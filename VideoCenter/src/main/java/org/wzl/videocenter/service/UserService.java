package org.wzl.videocenter.service;

import org.wzl.videocenter._do.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 卫志龙
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-07-14 17:08:49
*/
public interface UserService extends IService<User> {

    void register(User user);

    User login(User user);
}
