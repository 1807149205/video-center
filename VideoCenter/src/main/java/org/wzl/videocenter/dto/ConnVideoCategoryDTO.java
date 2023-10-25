package org.wzl.videocenter.dto;

import lombok.Data;

import java.util.List;

/**
 * 视频与分类DTO  1视频 -> 多分类
 *
 * @author: 卫志龙
 * @date: 2023-10-25 14:24
 */
@Data
public class ConnVideoCategoryDTO {

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 分类id集合
     */
    private List<Integer> categoryIds;

}
