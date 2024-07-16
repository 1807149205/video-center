package org.wzl.videocenter.service;

import org.wzl.videocenter._do.VideoCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wzl.videocenter.vo.VideoCategoryVO;

import java.util.List;

/**
* @author 卫志龙
* @description 针对表【t_video_category】的数据库操作Service
* @createDate 2024-07-14 18:23:14
*/
public interface VideoCategoryService extends IService<VideoCategory> {

    void add(VideoCategory videoCategory);

    VideoCategoryVO getAllById(String id);

    List<VideoCategoryVO> getAll();
}
