package org.wzl.videocenter.vo;

import lombok.Data;
import org.wzl.videocenter._do.TCategory;
import org.wzl.videocenter._do.TVideo;

import java.util.List;

/**
 * 视频-分类VO
 *
 * @author: 卫志龙
 * @date: 2023-10-25 17:53
 */
@Data
public class VideoCategoryVO {
    private TVideo video;
    private List<TCategory> categories;
}
