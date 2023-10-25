package org.wzl.videocenter.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 视频-分类DTO
 *
 * @author: 卫志龙
 * @date: 2023-10-25 17:08
 */
@Data
public class VideoCategoryDTO {

    private Integer videoId;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频的地址
     * -- GETTER --
     *  视频的地址

     */
    private String videoPath;

    /**
     * 视频图片地址
     * -- GETTER --
     *  视频图片地址

     */
    private String videoImgPath;

    /**
     * 创建时间
     * -- GETTER --
     *  创建时间

     */
    private LocalDateTime videoCreateDate;

    /**
     * 修改时间
     * -- GETTER --
     *  修改时间

     */
    private LocalDateTime videoUpdateDate;

    private Integer categoryId;

    /**
     * 分类名称
     * -- GETTER --
     *  分类名称

     */
    private String categoryName;

    /**
     * 创建时间
     * -- GETTER --
     *  创建时间

     */
    private LocalDateTime categoryCreateDate;

    /**
     * 更新时间
     * -- GETTER --
     *  更新时间

     */
    private LocalDateTime categoryUpdateDate;


}
