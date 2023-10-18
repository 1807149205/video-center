package org.wzl.videocenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wzl.videocenter._do.TVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wzl.videocenter.dto.TVideoDTO;

/**
* @author Administrator
* @description 针对表【t_video】的数据库操作Service
* @createDate 2023-10-13 12:03:56
*/
public interface TVideoService extends IService<TVideo> {

    void saveVideo(TVideo tVideo);

    IPage<TVideoDTO> getPage(Integer page, Integer size);
}
