package org.wzl.videocenter.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter.service.VideoService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 卫志龙
 * @Date: 2023/10/13/17:45
 * @Description:
 */
@RequestMapping("/video")
@RestController
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;

}
