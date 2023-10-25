package org.wzl.videocenter.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wzl.videocenter._do.TCategory;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter.service.TCategoryService;
import org.wzl.videocenter.service.TVideoCategoryService;
import org.wzl.videocenter.service.TVideoService;
import org.wzl.videocenter.vo.VideoCategoryVO;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
public class VideoCategoryWebController {

    @Resource
    private TVideoService tVideoService;

    @Resource
    private TCategoryService tCategoryService;

    @Resource
    private TVideoCategoryService tVideoCategoryService;

    @GetMapping("/videoCategory")
    public String videoCategory(Model model, Integer videoId) {
        TVideo tVideo = tVideoService.getById(videoId);
        model.addAttribute("video", tVideo);
        VideoCategoryVO allCategoriesByVideoId = tVideoCategoryService.getAllCategoriesByVideoId(videoId);
        model.addAttribute("videoCategory", allCategoriesByVideoId);
        return "videoCategory";
    }

    @GetMapping("/addCategory")
    public String addCategory() {
        return "addCategory";
    }

    @GetMapping("/categoryList")
    public String categoryList(Model model) {
        List<TCategory> list = tCategoryService.list();
        model.addAttribute("list", list);
        return "categoryList";
    }

}
