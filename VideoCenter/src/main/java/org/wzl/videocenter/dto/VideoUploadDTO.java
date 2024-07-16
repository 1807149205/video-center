package org.wzl.videocenter.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 卫志龙
 * @date: 2024年07月15日 16:14
 */
@Data
public class VideoUploadDTO {

    private MultipartFile file;

    private String userId;

    private String videoName;

}
