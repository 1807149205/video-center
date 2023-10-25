package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter._do.TCategory;
import org.wzl.videocenter._do.TVideoCategory;
import org.wzl.videocenter._enum.ResponseEnum;
import org.wzl.videocenter.dto.ConnVideoCategoryDTO;
import org.wzl.videocenter.dto.VideoCategoryDTO;
import org.wzl.videocenter.service.TCategoryService;
import org.wzl.videocenter.service.TVideoCategoryService;
import org.wzl.videocenter.utils.Resp;
import org.wzl.videocenter.vo.VideoCategoryVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频分类接口
 *
 * @author: 卫志龙
 * @date: 2023-10-25 13:31
 */
@RestController
@Slf4j
@RequestMapping("/video-category")
@CrossOrigin
public class VideoCategoryController {

    @Resource
    private TCategoryService categoryService;

    @Resource
    private TVideoCategoryService videoCategoryService;

    @GetMapping("/getById")
    public Resp<TCategory> getById(String categoryId) {
        TCategory byId = categoryService.getById(categoryId);
        if (null == byId) {
            return Resp.fail(ResponseEnum.PARAM_ERROR);
        } else {
            return Resp.ok(byId);
        }
    }

    @PostMapping("/save")
    public Resp<Boolean> save(@RequestBody TCategory category) {
        boolean save = categoryService.save(category);
        return Resp.ok(save);
    }

    @GetMapping("/page/{size}/{page}")
    public Resp<?> page(@PathVariable Integer page, @PathVariable Integer size) {
        return Resp.ok(categoryService.getPage(page, size));
    }

    @PostMapping("/delete")
    public Resp<Boolean> delete(@RequestBody TCategory category) {
        return Resp.ok(categoryService.removeById(category));
    }

    /**
     * 联系视频与分类的联系
     * @return
     */
    @PostMapping("/connVideoAndCategory")
    public Resp<Boolean> connVideoAndCategory(@RequestBody ConnVideoCategoryDTO connVideoCategoryDTO) {
        try {
            videoCategoryService.saveConnVideoCategory(connVideoCategoryDTO);
        } catch (Exception e) {
            log.info("保存视频失败！");
            e.printStackTrace();
            return Resp.fail(ResponseEnum.PARAM_ERROR, "保存失败");
        }
        return Resp.ok();
    }

    /**
     * 根据id删除视频-分类表的数据
     * @param videoCategory
     * @return
     */
    @PostMapping("/disConnVideoAndCategory")
    public Resp<Boolean> disConnVideoAndCategory(@RequestBody TVideoCategory videoCategory) {
        return Resp.ok(videoCategoryService.removeById(videoCategory));
    }

    /**
     * 获取
     * @param videoId
     * @return
     */
    @GetMapping("/getAllCategoriesByVideoId")
    public Resp<VideoCategoryVO> getAllCategoriesByVideoId(Integer videoId) {
        return Resp.ok(videoCategoryService.getAllCategoriesByVideoId(videoId));
    }



}
