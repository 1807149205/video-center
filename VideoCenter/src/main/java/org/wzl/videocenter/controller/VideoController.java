package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter.service.VideoService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: 卫志龙
 * @date: 2024年07月14日 22:46
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    public static final String VIDEO_PATH = "C:\\Users\\Administrator\\Desktop\\平凡之路 MV.mp4";

    @GetMapping("/video")
    public ResponseEntity<InputStreamResource> getVideo(
            @RequestHeader(value = "Range", required = false) String rangeHeader,
            @RequestParam(value = "start", defaultValue = "0") long start) throws IOException {

        File videoFile = new File(VIDEO_PATH);
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
        InputStreamResource resource = new InputStreamResource(inputStream) {
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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "video/mp4");
        headers.add("Accept-Ranges", "bytes");
        headers.add("Content-Length", String.valueOf(finalEndByte - finalStartByte + 1));
        headers.add("Content-Range", "bytes " + finalStartByte + "-" + finalEndByte + "/" + fileLength);

        return new ResponseEntity<>(resource, headers, HttpStatus.PARTIAL_CONTENT);
    }

}
