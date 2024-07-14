package org.wzl.videocenter._do;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_video_category_relation
 */
@TableName(value ="t_video_category_relation")
@Data
public class VideoCategoryRelation implements Serializable {
    /**
     * 
     */
    @TableId
    private String videoId;

    /**
     * 
     */
    @TableId
    private String categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}