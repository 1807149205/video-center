package org.wzl.videocenter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VideoResourceDTO {
    private Integer id;
    @JsonProperty("fileName")
    private String videoName;
}
