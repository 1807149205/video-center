package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter.config.VideoConfig;
import org.wzl.videocenter.service.VideoProcessingService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: 卫志龙
 * @date: 2024年07月16日 19:12
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private VideoProcessingService videoProcessingService;

    @Resource
    private VideoConfig videoConfig;

    @GetMapping("/getVideoPath")
    public Resp<?> getFile(String path) {
        return Resp.success(new HashMap<String, Object>() {{
            put("videoPath", videoConfig.getUploadPath() + path);
            put("imgPath", videoConfig.getImgPath() + path);
        }});
    }

//    @GetMapping("/t1")
//    public Resp<?> t1(String path) throws IOException, InterruptedException {
//        return Resp.success(videoProcessingService.generateThumbnails(path, 5));
//    }

}
