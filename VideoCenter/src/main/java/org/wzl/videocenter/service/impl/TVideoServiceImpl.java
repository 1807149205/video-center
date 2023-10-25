package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wzl.videocenter._do.TVideo;
import org.wzl.videocenter.dto.TVideoDTO;
import org.wzl.videocenter.service.TVideoService;
import org.wzl.videocenter.mapper.TVideoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 卫志龙
* @description 针对表【t_video】的数据库操作Service实现
* @createDate 2023-10-13 12:03:56
*/
@Service
public class TVideoServiceImpl extends ServiceImpl<TVideoMapper, TVideo>
    implements TVideoService{

    @Override
    public void saveVideo(TVideo tVideo) {
        tVideo.setUpdateDate(LocalDateTime.now());
        save(tVideo);
    }

    @Override
    public IPage<TVideoDTO> getPage(Integer page, Integer size) {
        IPage<TVideo> tVideoIPage = new Page<>();
        tVideoIPage.setSize(size);
        tVideoIPage.setCurrent(page);
        IPage<TVideo> selectPage = this.baseMapper.selectPage(tVideoIPage, null);
        List<TVideo> records = selectPage.getRecords();
        List<TVideoDTO> collect = records.stream()
                .map(v -> {
                    TVideoDTO tVideoDTO = new TVideoDTO();
                    tVideoDTO.setVideoName(v.getVideoName());
                    tVideoDTO.setVideoImgPath(v.getVideoImgPath());
                    tVideoDTO.setVideoPath(v.getVideoPath());
                    tVideoDTO.setId(v.getId());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    if (null != v.getCreateDate()) {
                        tVideoDTO.setCreateDate(v.getCreateDate().format(formatter));
                    }
                    if (null != v.getUpdateDate()) {
                        tVideoDTO.setUpdateDate(v.getUpdateDate().format(formatter));
                    }
                    return tVideoDTO;
                }).collect(Collectors.toList());
        IPage<TVideoDTO> resPage = new Page<>();
        resPage.setCurrent(selectPage.getPages());
        resPage.setSize(selectPage.getSize());
        resPage.setRecords(collect);
        resPage.setTotal(selectPage.getTotal());
        resPage.setPages(selectPage.getPages());
        resPage.setCurrent(page);
        return resPage;
    }
}




