package org.wzl.videocenter.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 视频DTO
 *
 * @author: 卫志龙
 * @date: 2023-10-16 21:43
 */
@Data
public class TVideoDTO {
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    private String createDate;

    /**
     * 修改时间
     * -- GETTER --
     *  修改时间

     */
    private String updateDate;
}
