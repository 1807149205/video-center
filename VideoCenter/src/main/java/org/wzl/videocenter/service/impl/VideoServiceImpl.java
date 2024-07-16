package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.wzl.videocenter._do.Video;
import org.wzl.videocenter._do.VideoCategoryRelation;
import org.wzl.videocenter.bo.VideoChunkBO;
import org.wzl.videocenter.dto.VideoUploadDTO;
import org.wzl.videocenter.exception.BizException;
import org.wzl.videocenter.service.VideoCategoryRelationService;
import org.wzl.videocenter.service.VideoService;
import org.wzl.videocenter.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import org.wzl.videocenter.utils.IdGen;
import org.wzl.videocenter.utils.VideoUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* @author 卫志龙
* @description 针对表【t_video】的数据库操作Service实现
* @createDate 2024-07-14 18:23:11
*/
@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

    @Value("${video.upload-path}")
    private String uploadPath;

    @Resource
    private VideoCategoryRelationService videoCategoryRelationService;

    private static final List<String> ALLOWED_VIDEO_TYPES = Arrays.asList("video/mp4", "video/x-msvideo", "video/x-matroska");


    @Override
    public VideoChunkBO getVideoChunk(String rangeHeader, long start, String videoPath) {

        VideoChunkBO videoChunkBO = new VideoChunkBO();
        InputStreamResource resource = null;
        HttpHeaders headers = null;

        try {
            File videoFile = new File(videoPath);
            long fileLength = videoFile.length();

            // 默认的分段大小（3分钟的视频片段，具体大小需根据视频比特率计算）
            long chunkSize = 3 * 60 * 1024 * 1024; // 假设视频比特率为1MB/s

            // 计算请求的起始和结束字节
            long startByte = start;
            long endByte = Math.min(startByte + chunkSize - 1, fileLength - 1);

            // 如果请求头中包含Range信息，则覆盖start和end字节
            if (rangeHeader != null && !rangeHeader.isEmpty()) {
                List<HttpRange> ranges = HttpRange.parseRanges(rangeHeader);
                if (!ranges.isEmpty()) {
                    HttpRange range = ranges.get(0);
                    startByte = range.getRangeStart(fileLength);
                    endByte = range.getRangeEnd(fileLength);
                }
            }

            // 创建输入流和Resource对象
            InputStream inputStream = new FileInputStream(videoFile);
            inputStream.skip(startByte);
            final long finalStartByte = startByte;
            final long finalEndByte = endByte;
            resource = new InputStreamResource(inputStream) {
                @Override
                public long contentLength() {
                    return finalEndByte - finalStartByte + 1;
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return inputStream;
                }
            };

            // 设置响应头
            headers = new HttpHeaders();

            headers.add("Content-Type", "video/mp4");
            headers.add("Accept-Ranges", "bytes");
            headers.add("Content-Length", String.valueOf(finalEndByte - finalStartByte + 1));
            headers.add("Content-Range", "bytes " + finalStartByte + "-" + finalEndByte + "/" + fileLength);

        } catch (Exception e) {
            log.error("系统错误", e);
            throw new BizException("视频获取失败");
        }

        videoChunkBO.setHeaders(headers);
        videoChunkBO.setResource(resource);

        return videoChunkBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(MultipartFile file, String videoName, String userId) throws IOException {
        validateFile(file);

        if (ObjectUtils.isEmpty(userId)) {
            throw new BizException("用户id为空");
        }
        if (ObjectUtils.isEmpty(videoName)) {
            throw new BizException("电影名称为空");
        }

//        Video video = new Video();
//
//        String videoId = IdGen.getId();
//        video.setId(videoId);
//        video.setVideoTime(VideoUtil.getVideoDuration(dest));
//        video.setStatus(1);
//        video.setUId(userId);
//        video.setVideoName(videoName);
//
//        save(video);

//        String fileName = videoId + ".mp4";
//        String path = uploadPath + fileName;
//        File dest = new File(uploadPath, fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        log.info("储存路径:{}", fileName + uploadPath);
//        file.transferTo(dest);

//        FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(targetFile.toPath()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file) {
        log.info("第一次上传开始...");
        String videoId = IdGen.getId();
        String fileName = videoId + ".mp4";
        String path = uploadPath + fileName;
        File dest = new File(uploadPath, fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        log.info("储存路径:{}", uploadPath + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new BizException("文件上传失败");
        }

        Video video = new Video();
        video.setId(videoId);
        video.setVideoTime(VideoUtil.getVideoDuration(dest));
        video.setStatus(0);
        log.info("video: {}", video);
        save(video);

        log.info("第一次上传结束...");
        return videoId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload2(VideoUploadDTO videoUploadDTO) {
        log.info("第二次上传开始...");
        Video video = getById(videoUploadDTO.getVideoId());
        video.setStatus(1);
        video.setUId(videoUploadDTO.getUserId());
        video.setVideoName(videoUploadDTO.getVideoName());
        updateById(video);

        List<VideoCategoryRelation> collect = videoUploadDTO.getCategoryIds().stream().map(i -> {
            VideoCategoryRelation videoCategoryRelation = new VideoCategoryRelation();
            videoCategoryRelation.setVideoId(videoUploadDTO.getVideoId());
            videoCategoryRelation.setCategoryId(i);
            return videoCategoryRelation;
        }).collect(Collectors.toList());
        boolean savedBatch = videoCategoryRelationService.saveBatch(collect);

        log.info("videoCategoryRelation: {}", savedBatch);

        log.info("第二次上传结束...");
    }


    private void validateFile(MultipartFile file) {
        if (null == file) {
            throw new BizException("file为null");
        }

        if (file.isEmpty()) {
            throw new BizException("上传的文件不能为空");
        }

        if (file.getSize() > 300 * 1024 * 1024) { // 300MB
            throw new BizException("上传的文件大小不能超过300MB");
        }

        String contentType = file.getContentType();
        if (!ALLOWED_VIDEO_TYPES.contains(contentType)) {
            throw new BizException("仅支持上传视频文件");
        }
    }
}




