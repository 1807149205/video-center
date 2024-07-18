package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.wzl.videocenter._do.Video;
import org.wzl.videocenter._do.VideoCategory;
import org.wzl.videocenter._do.VideoCategoryRelation;
import org.wzl.videocenter.constant.VideoCategoryConstant;
import org.wzl.videocenter.service.VideoCategoryRelationService;
import org.wzl.videocenter.service.VideoCategoryService;
import org.wzl.videocenter.mapper.VideoCategoryMapper;
import org.springframework.stereotype.Service;
import org.wzl.videocenter.utils.IdGen;
import org.wzl.videocenter.vo.VideoCategoryRawVO;
import org.wzl.videocenter.vo.VideoCategoryVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 卫志龙
* @description 针对表【t_video_category】的数据库操作Service实现
* @createDate 2024-07-14 18:23:14
*/
@Slf4j
@Service
public class VideoCategoryServiceImpl extends ServiceImpl<VideoCategoryMapper, VideoCategory>
    implements VideoCategoryService{

    @Resource
    private VideoCategoryRelationService videoCategoryRelationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(VideoCategory videoCategory) {
        videoCategory.setId(IdGen.getId());
        save(videoCategory);
    }

    @Override
    public VideoCategoryVO getAllById(String id) {
        VideoCategory categoryDO = getById(id);
        VideoCategoryVO vo = getVideoCategoryVO(categoryDO);
        List<VideoCategoryVO> children = new ArrayList<>();
        List<VideoCategory> doList = list(Wrappers.<VideoCategory>lambdaQuery()
                .eq(VideoCategory::getParentId, categoryDO.getId()));
        for (VideoCategory category : doList) {
            children.add(getAllById(category.getId()));
        }
        vo.setChildren(children.isEmpty() ? null : children);
        return vo;
    }

    @Override
    public List<VideoCategoryVO> getAll() {
        List<VideoCategory> topList = list(Wrappers.<VideoCategory>lambdaQuery()
                .eq(VideoCategory::getParentId, VideoCategoryConstant.TOP_LEVEL_ID));
        return topList.stream().map(t -> getAllById(t.getId())).collect(Collectors.toList());
    }

    @Override
    public void saveVideoCategoryBatch(Video video, List<String> categoryIds) {
        List<VideoCategoryRelation> collect = categoryIds.stream().map(i -> {
            VideoCategoryRelation videoCategoryRelation = new VideoCategoryRelation();
            videoCategoryRelation.setVideoId(video.getId());
            videoCategoryRelation.setCategoryId(i);
            return videoCategoryRelation;
        }).collect(Collectors.toList());

        boolean savedBatch = videoCategoryRelationService.saveBatch(collect);

        log.info("videoCategoryRelation: {}", savedBatch);
    }

    @Override
    public List<VideoCategoryRawVO> getVideoCategory(Video video) {
        List<VideoCategoryRelation> videoCategoryRelations = videoCategoryRelationService.
                list(Wrappers.<VideoCategoryRelation>lambdaQuery()
                        .eq(VideoCategoryRelation::getVideoId, video.getId()));
        return videoCategoryRelations.stream().map(r -> {
            VideoCategoryRawVO vo = new VideoCategoryRawVO();
            VideoCategory videoCategory = getById(r.getCategoryId());
            vo.setCreateTime(videoCategory.getCreateDate());
            vo.setUpdateTime(videoCategory.getUpdateDate());
            vo.setValue(videoCategory.getId());
            vo.setText(videoCategory.getCategoryName());
            vo.setPId(videoCategory.getParentId());
            return vo;
        }).collect(Collectors.toList());
    }


    private VideoCategoryVO getVideoCategoryVO(VideoCategory categoryDO) {
        VideoCategoryVO vo = new VideoCategoryVO();
        vo.setText(categoryDO.getCategoryName());
        vo.setValue(categoryDO.getId());
        vo.setPId(categoryDO.getParentId());
        vo.setCreateTime(categoryDO.getCreateDate());
        vo.setUpdateTime(categoryDO.getUpdateDate());
        return vo;
    }


}




