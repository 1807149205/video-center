package org.wzl.videocenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzl.videocenter._do.User;
import org.wzl.videocenter.service.UserService;
import org.wzl.videocenter.utils.Resp;

import javax.annotation.Resource;

/**
 * @author: 卫志龙
 * @date: 2024年07月14日 17:09
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Resp<?> register(@RequestBody User user) {
        userService.register(user);
        return Resp.success();
    }

    @PostMapping("/login")
    public Resp<User> login(@RequestBody User user) {
        return Resp.success(userService.login(user));
    }

}
