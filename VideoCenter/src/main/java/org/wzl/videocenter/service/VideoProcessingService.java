package org.wzl.videocenter.service;

import org.wzl.videocenter._do.Video;

import java.io.IOException;
import java.util.List;

/**
 * @author: 卫志龙
 * @date: 2024年07月16日 19:07
 */
public interface VideoProcessingService {


    void generateThumbnails(Video video);
}
