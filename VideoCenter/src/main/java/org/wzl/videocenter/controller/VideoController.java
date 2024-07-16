package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wzl.videocenter.bo.VideoChunkBO;
import org.wzl.videocenter.dto.VideoUploadDTO;
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
            @RequestParam(value = "start", defaultValue = "0") long start) {

        VideoChunkBO videoChunkBO = videoService.getVideoChunk(rangeHeader, start, VIDEO_PATH);
        return new ResponseEntity<>(videoChunkBO.getResource(), videoChunkBO.getHeaders(), HttpStatus.PARTIAL_CONTENT);
    }

    @PostMapping("/upload1")
    public Resp<?> upload(@RequestBody MultipartFile file) throws IOException {
//    public Resp<?> upload(MultipartFile file, String videoName, String userId) throws IOException {
        VideoUploadDTO videoUploadDTO = new VideoUploadDTO();
//        videoService.upload(videoUploadDTO.getFile(), videoUploadDTO.getVideoName(), videoUploadDTO.getUserId());
        String videoId = videoService.upload(file);
        return Resp.success(videoId);
    }

    @PostMapping("/upload2")
    public Resp<?> upload2(@RequestBody VideoUploadDTO videoUploadDTO) {
        videoService.upload2(videoUploadDTO);
        return Resp.success();
    }


}
