package org.wzl.videocenter.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wzl.videocenter._do.Video;
import org.wzl.videocenter.exception.BizException;
import org.wzl.videocenter.service.VideoProcessingService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VideoProcessingServiceImpl implements VideoProcessingService {

    @Value("${video.img-path}")
    private String imgPath;

    @Value("${video.upload-path}")
    private String uploadPath;

    private static final String FFmpeg_PATH = "ffmpeg";

    private static final Integer SPLIT_COUNT = 5;

    @Override
    public void generateThumbnails(Video video) {
        String videoPath = uploadPath + video.getId() + ".mp4";
        List<String> splitVideoTime = splitVideoTime(video.getVideoTime(), SPLIT_COUNT);
        File imgPathFile = new File(imgPath);
        if (!imgPathFile.getParentFile().exists()) {
            imgPathFile.getParentFile().mkdirs();
        }

        for (int i = 0; i < splitVideoTime.size(); i++) {
            try {
                captureFrame(videoPath, splitVideoTime.get(i), getVideoImgPath(video, i++));
            } catch (Exception e) {
                log.error("生成视频图片错误，i:{}, video:{}", i, video, e);
                throw new BizException("生成视频图片错误");
            }
        }

    }

    private String getVideoImgPath(Video video, int index) {
        return imgPath + video.getId() + "_" + index + ".jpg";
    }

    private void captureFrame(String videoPath, String time, String outputImagePath) throws IOException, InterruptedException {
        // 构建FFmpeg命令
        String[] command = {
                FFmpeg_PATH,
                "-i", videoPath,
                "-ss", time,
                "-vframes", "1",
                outputImagePath
        };

        // 使用ProcessBuilder来执行命令
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        try {
            Process process = processBuilder.start();

            // 获取命令的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("FFmpeg命令执行失败，退出代码：" + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<String> splitVideoTime(int totalSeconds, int splitCount) {
        List<String> splitTimes = new ArrayList<>();

        if (splitCount <= 0 || totalSeconds <= 0) {
            return splitTimes;
        }

        int interval = totalSeconds / splitCount;
        for (int i = 1; i <= splitCount; i++) {
            int seconds = interval * i;
            splitTimes.add(formatTime(seconds));
        }

        return splitTimes;
    }

    private static String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
