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
 * 电影分类表
 * @TableName t_category
 */
@Data
@ToString
@TableName(value ="t_category")
public class TCategory implements Serializable {
    /**
     * 主键id
     * -- GETTER --
     *  主键id

     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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