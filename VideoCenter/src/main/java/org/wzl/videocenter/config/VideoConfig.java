package org.wzl.videocenter.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Paths;

@Slf4j
@Getter
@Configuration
@ConfigurationProperties(prefix = "video")
public class VideoConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${video.upload-path}")
    private String uploadPath;

    @Value("${video.img-path}")
    private String imgPath;

    @PostConstruct
    public void init() {
        if (profile.equals("dev")) {
            setDevProfile();
        }
        if (profile.equals("prod")) {
            setProdProfile();
        }
    }

    private void setDevProfile() {
        // 获取resources目录的绝对路径
        String resourcePath = Paths.get("src/main/resources").toAbsolutePath().toString();

        this.uploadPath = resourcePath + File.separator + "video" + File.separator;
        this.imgPath = resourcePath + File.separator + "img" + File.separator;

        log();
    }

    private void setProdProfile() {
        log();
    }

    private void log() {
        log.info("profile: {}", this.profile);
        log.info("Upload Path: {}", this.uploadPath);
        log.info("Image Path: {}", this.imgPath);
    }

}
