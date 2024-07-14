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
 * @TableName t_video
 */
@TableName(value ="t_video")
@Data
public class Video implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String videoName;

    /**
     * 视频时间，单位秒
     */
    private Integer videoTime;

    /**
     * 
     */
    private String uId;

    /**
     * 
     */
    private Integer status;

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