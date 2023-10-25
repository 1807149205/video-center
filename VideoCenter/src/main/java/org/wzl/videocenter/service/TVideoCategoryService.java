package org.wzl.videocenter.service;

import org.wzl.videocenter._do.TVideoCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wzl.videocenter.dto.ConnVideoCategoryDTO;
import org.wzl.videocenter.dto.VideoCategoryDTO;
import org.wzl.videocenter.vo.VideoCategoryVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_video_category】的数据库操作Service
* @createDate 2023-10-13 12:03:59
*/
public interface TVideoCategoryService extends IService<TVideoCategory> {

    /**
     * 保存视频与分类之间的联系
     * @param connVideoCategoryDTO
     */
    void saveConnVideoCategory(ConnVideoCategoryDTO connVideoCategoryDTO);

    VideoCategoryVO getAllCategoriesByVideoId(Integer videoId);
}
