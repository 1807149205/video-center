package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.wzl.videocenter._do.TCategory;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter._do.TVideoCategory;
import org.wzl.videocenter.dto.ConnVideoCategoryDTO;
import org.wzl.videocenter.dto.VideoCategoryDTO;
import org.wzl.videocenter.service.TCategoryService;
import org.wzl.videocenter.service.TVideoCategoryService;
import org.wzl.videocenter.mapper.TVideoCategoryMapper;
import org.springframework.stereotype.Service;
import org.wzl.videocenter.service.TVideoService;
import org.wzl.videocenter.vo.VideoCategoryVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 卫志龙
* @description 针对表【t_video_category】的数据库操作Service实现
* @createDate 2023-10-13 12:03:59
*/
@Service
public class TVideoCategoryServiceImpl extends ServiceImpl<TVideoCategoryMapper, TVideoCategory>
    implements TVideoCategoryService{

    /**
     * 视频service
     */
    @Resource
    private TVideoService videoService;

    /**
     * 分类service
     */
    @Resource
    private TCategoryService categoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveConnVideoCategory(ConnVideoCategoryDTO connVideoCategoryDTO) {
        List<Integer> categoryIds = connVideoCategoryDTO.getCategoryIds();
        Integer videoId = connVideoCategoryDTO.getVideoId();
        categoryIds.forEach(id -> {
            TVideoCategory videoCategory = new TVideoCategory();
            videoCategory.setVideoId(videoId);
            videoCategory.setCategoryId(id);
            save(videoCategory);
        });
    }

    @Override
    public VideoCategoryVO getAllCategoriesByVideoId(Integer videoId) {
        List<VideoCategoryDTO> allCategoriesByVideoId = this.baseMapper.getAllCategoriesByVideoId(videoId);
        VideoCategoryVO videoCategoryVO = new VideoCategoryVO();
        List<TCategory> list = allCategoriesByVideoId
                .stream()
                .map(vc -> {
                    TVideo video = new TVideo();
                    video.setId(vc.getVideoId());
                    video.setVideoImgPath(vc.getVideoImgPath());
                    video.setVideoName(vc.getVideoName());
                    video.setVideoPath(vc.getVideoPath());
                    video.setCreateDate(vc.getVideoCreateDate());
                    video.setUpdateDate(vc.getVideoUpdateDate());
                    videoCategoryVO.setVideo(video);
                    TCategory category = new TCategory();
                    category.setCategoryName(vc.getCategoryName());
                    category.setId(vc.getCategoryId());
                    category.setCreateDate(vc.getCategoryCreateDate());
                    category.setUpdateDate(vc.getCategoryUpdateDate());
                    return category;
                })
                .collect(Collectors.toList());
        videoCategoryVO.setCategories(list);
        return videoCategoryVO;
    }

}




