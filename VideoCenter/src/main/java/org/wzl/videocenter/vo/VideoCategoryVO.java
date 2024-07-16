package org.wzl.videocenter.vo;

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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<VideoCategoryVO> children;

}
