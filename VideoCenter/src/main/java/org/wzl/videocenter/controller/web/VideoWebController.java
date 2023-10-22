package org.wzl.videocenter.controller.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter.dto.TVideoDTO;
import org.wzl.videocenter.service.TVideoService;

import javax.annotation.Resource;

/**
 * Web页面
 *
 * @author: 卫志龙
 * @date: 2023-10-13 22:33
 */
@Controller
public class VideoWebController {

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

    /**
     * 视频的分页查询列表
     * @param model
     * @param page 当前多少页
     * @return
     */
    @GetMapping("/videoList/{page}")
    public String videoList(Model model, @PathVariable Integer page) {
        IPage<TVideoDTO> list = tVideoService.getPage(page, 10);
        model.addAttribute("list", list);
        return "videoList";
    }

    @GetMapping("/videoDetail")
    public String videoDetail(String id, Model model) {
        TVideo video = tVideoService.getOne(Wrappers.<TVideo>lambdaQuery()
                .eq(TVideo::getId, Integer.parseInt(id)));
        model.addAttribute("video", video);
        return "videoDetail";
    }


}
