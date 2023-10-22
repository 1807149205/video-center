package org.wzl.videocenter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter.dto.VideoResourceDTO;
import org.wzl.videocenter.feign.VideoResourceFeign;
import org.wzl.videocenter.service.TVideoService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class VideoCenterApplicationTests {

    @Resource
    private VideoResourceFeign videoResourceFeign;

    @Resource
    private TVideoService tVideoService;

    @Test
    void contextLoads() {
        List<VideoResourceDTO> dataJson = videoResourceFeign.getDataJson();
        log.info("获取到的json为:{}", dataJson);
        dataJson.forEach(d -> {
            TVideo tVideo = new TVideo();
            String videoName = d.getVideoName();
            String newVideoName = videoName.substring(0, videoName.length() - 4);
            tVideo.setVideoName(newVideoName);
            tVideo.setVideoPath("https://1807149205.github.io/GITHUB_pages/video/" + newVideoName + ".mp4");
            tVideo.setVideoImgPath("https://1807149205.github.io/GITHUB_pages/video_img/" + newVideoName + ".jpg");
            log.info("video: {}" , tVideo);
            tVideoService.saveVideo(tVideo);
        });
    }

}
