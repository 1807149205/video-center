package org.wzl.videocenter.service;

import org.springframework.web.multipart.MultipartFile;
import org.wzl.videocenter._do.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wzl.videocenter.bo.VideoChunkBO;
import org.wzl.videocenter.dto.VideoUploadDTO;

import java.io.IOException;

/**
* @author 卫志龙
* @description 针对表【t_video】的数据库操作Service
* @createDate 2024-07-14 18:23:11
*/
public interface VideoService extends IService<Video> {

    VideoChunkBO getVideoChunk(String rangeHeader, long start, String videoPath);

    void upload(MultipartFile file, String videoName, String userId) throws IOException;

    String upload(MultipartFile file);

    void upload2(VideoUploadDTO videoUploadDTO);
}
