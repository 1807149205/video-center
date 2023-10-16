package org.wzl.videocenter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter._enum.ResponseEnum;
import org.wzl.videocenter.mapper.TVideoMapper;
import org.wzl.videocenter.service.TVideoService;
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
@Slf4j
public class VideoController {

    @Resource
    private TVideoService tVideoService;

    @Resource
    private TVideoMapper tVideoMapper;

    @GetMapping("/page/{size}/{page}")
    public Resp<?> page(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<TVideo> tVideoIPage = new Page<>();
        tVideoIPage.setSize(size);
        tVideoIPage.setCurrent(page);
        IPage<TVideo> res = tVideoMapper.selectPage(tVideoIPage, null);
        return Resp.ok(res);
    }

    @PostMapping("/save")
    public Resp<Boolean> save(@RequestBody TVideo tVideo) {
        tVideoService.saveVideo(tVideo);
        return Resp.ok();
    }

    @PostMapping("/delete")
    public Resp<Boolean> delete(@NotNull @RequestBody TVideo tVideo) {
        if (null == tVideo.getId()) {
            return Resp.fail(ResponseEnum.PARAM_ERROR);
        }
        tVideoService.removeById(tVideo);
        return Resp.ok();
    }

}
