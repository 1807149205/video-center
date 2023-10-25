package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wzl.videocenter._do.TCategory;
import org.wzl.videocenter.service.TCategoryService;
import org.wzl.videocenter.mapper.TCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 卫志龙
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2023-10-13 12:03:34
*/
@Service
public class TCategoryServiceImpl extends ServiceImpl<TCategoryMapper, TCategory>
    implements TCategoryService{

    @Override
    public IPage<TCategory> getPage(Integer page, Integer size) {
        IPage<TCategory> resPage = new Page<>(page, size);
        return page(resPage);
    }
}




