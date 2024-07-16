package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.wzl.videocenter._do.VideoCategory;
import org.wzl.videocenter.service.VideoCategoryService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/category")
public class VideoCategoryController {

    @Resource
    private VideoCategoryService videoCategoryService;

    @PostMapping("/add")
    public Resp<?> add(@RequestBody VideoCategory videoCategory) {
        videoCategoryService.add(videoCategory);
        return Resp.success();
    }

    @GetMapping("/getById")
    public Resp<?> getById(@RequestParam("id") String id) {
        return Resp.success(videoCategoryService.getAllById(id));
    }

    @GetMapping("/getAll")
    public Resp<?> getAll() {
        return Resp.success(videoCategoryService.getAll());
    }

}
