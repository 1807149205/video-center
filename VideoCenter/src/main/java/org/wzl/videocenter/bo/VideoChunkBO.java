package org.wzl.videocenter.bo;

import lombok.Data;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;

/**
 * @author: 卫志龙
 * @date: 2024年07月15日 14:47
 */
@Data
public class VideoChunkBO {

    HttpHeaders headers;

    InputStreamResource resource;

}
