package org.wzl.videocenter.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VideoCategoryVO {

    //id
    private String value;
    //categoryName
    private String text;
    private String pId;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime updateTime;
    private List<VideoCategoryVO> children;

}
