package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter.service.CheckService;

import javax.annotation.Resource;

/**
 * 测试服务是否可用
 *
 * @author: 卫志龙
 * @date: 2023-10-19 09:17
 */
@RestController
@Slf4j
@RequestMapping("/check")
public class CheckController {

    @Resource
    private CheckService checkService;


    @GetMapping("/doCheck")
    public String doCheck() throws Exception {
        int checkDB = checkService.checkDb();
        String pong = checkService.checkRedis();
        log.info("db:{},redis:{}", checkDB, pong);
        return "success";
    }

}
