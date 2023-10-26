package org.wzl.videocenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wzl.videocenter._do.TCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 卫志龙
* @description 针对表【t_category】的数据库操作Service
* @createDate 2023-10-13 12:03:34
*/
public interface TCategoryService extends IService<TCategory> {

    IPage<TCategory> getPage(Integer page, Integer size);

}
