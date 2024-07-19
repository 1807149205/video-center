package org.wzl.videocenter.service;

import org.wzl.videocenter._do.Video;

import java.io.IOException;
import java.util.List;

/**
 * @author: 卫志龙
 * @date: 2024年07月16日 19:07
 */
public interface VideoProcessingService {

    /**
     *  获取视频图片截图
     *
     * @param: [org.wzl.videocenter._do.Video]
     * @return: void
     * @author: 卫志龙
     * @Date: 2024/7/19 15:26
     */
    void generateThumbnails(Video video);

}
