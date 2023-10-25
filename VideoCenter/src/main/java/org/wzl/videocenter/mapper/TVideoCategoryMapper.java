package org.wzl.videocenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wzl.videocenter._do.TVideoCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wzl.videocenter.dto.VideoCategoryDTO;

import java.util.List;

/**
* @author 卫志龙
* @description 针对表【t_video_category】的数据库操作Mapper
* @createDate 2023-10-13 12:03:59
* @Entity org.wzl.videocenter._do.TVideoCategory
*/
@Mapper
public interface TVideoCategoryMapper extends BaseMapper<TVideoCategory> {

    List<VideoCategoryDTO> getAllCategoriesByVideoId(Integer videoId);
}




