package org.wzl.videocenter._do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 电影分类实体关联表
 * @TableName t_video_category
 */
@Data
@ToString
@TableName(value ="t_video_category")
public class TVideoCategory implements Serializable {
    /**
     * 主键id
     * -- GETTER --
     *  主键id

     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 视频id
     * -- GETTER --
     *  视频id

     */
    private Integer videoId;

    /**
     * 分类id
     * -- GETTER --
     *  分类id

     */
    private Integer categoryId;

    /**
     * 创建时间
     * -- GETTER --
     *  创建时间

     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 更新时间
     * -- GETTER --
     *  更新时间

     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}