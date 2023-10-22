package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter.dto.VideoResourceDTO;
import org.wzl.videocenter.feign.VideoResourceFeign;
import org.wzl.videocenter.service.TVideoService;
import org.wzl.videocenter.service.VideoService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Resource
    private VideoResourceFeign videoResourceFeign;

    @Resource
    private TVideoService tVideoService;

    @GetMapping("/loadFile")
    public Resp<Boolean> loadFile() {
        List<VideoResourceDTO> dataJson = videoResourceFeign.getDataJson();
        log.info("获取到的json为:{}", dataJson);
        dataJson.forEach(d -> {
            TVideo tVideo = new TVideo();
            String videoName = d.getVideoName();
            String newVideoName = videoName.substring(0, videoName.length() - 4);
            tVideo.setVideoName(newVideoName);
            tVideo.setVideoPath(newVideoName + ".mp4");
            tVideo.setVideoImgPath(newVideoName + ".jpg");
            log.info("video: {}" , tVideo);
            tVideoService.saveVideo(tVideo);
        });
        return Resp.ok();
    }

}
