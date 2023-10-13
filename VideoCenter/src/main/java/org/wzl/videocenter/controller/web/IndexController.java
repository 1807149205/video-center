package org.wzl.videocenter.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter.service.TVideoService;

import javax.annotation.Resource;

/**
 * Web页面
 *
 * @author: 卫志龙
 * @date: 2023-10-13 22:33
 */
@Controller
public class IndexController {

    @Resource
    private TVideoService tVideoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "index");
        return "index";
    }

    @GetMapping("/addVideo")
    public String addVideo(Model model) {
        return "addVideo";
    }

    @RequestMapping(value = "/addVideo")
    public String addVideoPost(Model model, @ModelAttribute TVideo tVideo) {
        boolean save = tVideoService.save(tVideo);
        if (save) {
            model.addAttribute("msg", "添加成功");
        } else {
            model.addAttribute("msg", "添加失败");
        }
        return "addVideo";
    }

    @GetMapping("/videoList")
    public String videoList(Model model) {
        return "videoList";
    }

}
