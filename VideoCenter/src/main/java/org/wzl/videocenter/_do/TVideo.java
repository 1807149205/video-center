package org.wzl.videocenter._do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 电影实体表
 * @TableName t_video
 */
@Data
@TableName(value ="t_video")
public class TVideo implements Serializable {
    /**
     * 主键id
     * -- GETTER --
     *  主键id

     */
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 修改时间
     * -- GETTER --
     *  修改时间

     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TVideo other = (TVideo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVideoPath() == null ? other.getVideoPath() == null : this.getVideoPath().equals(other.getVideoPath()))
            && (this.getVideoImgPath() == null ? other.getVideoImgPath() == null : this.getVideoImgPath().equals(other.getVideoImgPath()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVideoPath() == null) ? 0 : getVideoPath().hashCode());
        result = prime * result + ((getVideoImgPath() == null) ? 0 : getVideoImgPath().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoPath=").append(videoPath);
        sb.append(", videoImgPath=").append(videoImgPath);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}