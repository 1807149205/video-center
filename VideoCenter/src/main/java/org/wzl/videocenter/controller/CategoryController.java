package org.wzl.videocenter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter._do.TCategory;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter._enum.ResponseEnum;
import org.wzl.videocenter.service.TCategoryService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;

/**
 * 分类controller
 *
 * @author: 卫志龙
 * @date: 2023-10-13 22:19
 */
@RequestMapping("/category")
@RestController
@CrossOrigin
@Slf4j
public class CategoryController {

    @Resource
    private TCategoryService tCategoryService;

    @GetMapping("/page/{size}/{page}")
    public Resp<?> page(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<TCategory> tVideoIPage = new Page<>();
        tVideoIPage.setSize(size);
        tVideoIPage.setCurrent(page);
        IPage<TCategory> tCategoryIPage = tCategoryService.page(tVideoIPage);
        return Resp.ok(tCategoryIPage);
    }

    @PostMapping("/save")
    public Resp<Boolean> save(@RequestBody TCategory tCategory) {
        tCategoryService.save(tCategory);
        return Resp.ok();
    }

    @PostMapping("/delete")
    public Resp<Boolean> delete(@RequestBody TCategory tCategory) {
        if (null == tCategory.getId()) {
            return Resp.fail(ResponseEnum.PARAM_ERROR);
        }
        tCategoryService.removeById(tCategory);
        return Resp.ok();
    }

}
