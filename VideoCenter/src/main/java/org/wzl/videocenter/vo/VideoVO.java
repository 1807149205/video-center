package org.wzl.videocenter.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wzl.videocenter._do.VideoCategory;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VideoVO {

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
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime createDate;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime updateDate;

    private List<VideoCategoryRawVO> videoCategory;

}
