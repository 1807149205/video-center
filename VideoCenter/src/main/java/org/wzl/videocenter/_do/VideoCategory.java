package org.wzl.videocenter._do;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName t_video_category
 */
@TableName(value ="t_video_category")
@Data
public class VideoCategory implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String categoryName;

    /**
     * 
     */
    private String parentId;

    /**
     * 
     */
    private LocalDateTime createDate;

    /**
     * 
     */
    private LocalDateTime updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}