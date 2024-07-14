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
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String videoId;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Integer likeCount;

    /**
     * 
     */
    private Integer dislikeCount;

    /**
     * 
     */
    private String pId;

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