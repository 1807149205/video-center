package org.wzl.videocenter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.wzl.videocenter.dto.VideoResourceDTO;

import java.util.List;

@FeignClient(url = "https://1807149205.github.io/GITHUB_pages", name = "videoResource")
public interface VideoResourceFeign {

    @GetMapping("/data/data.json")
    List<VideoResourceDTO> getDataJson();

}
