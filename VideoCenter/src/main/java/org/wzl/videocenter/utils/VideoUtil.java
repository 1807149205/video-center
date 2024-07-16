package org.wzl.videocenter.utils;

import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@Slf4j
public class VideoUtil {

    public static int getVideoDuration(MultipartFile file) {
        SeekableByteChannel sbc = null;
        File tempFile = null;
        try {
            tempFile = File.createTempFile("video", file.getOriginalFilename());
            file.transferTo(tempFile);
            sbc = null;
            sbc = NIOUtils.readableChannel(tempFile);
            FrameGrab grab = FrameGrab.createFrameGrab(sbc);
            return (int) grab.getVideoTrack().getMeta().getTotalDuration();
        } catch (Exception e) {
            log.error("获取视频时间错误", e);
            return 0;
        } finally {
            try {
                if (sbc != null) {
                    sbc.close();
                }
                Files.delete(tempFile.toPath());
            } catch (Exception e) {

            }
        }
    }
}
