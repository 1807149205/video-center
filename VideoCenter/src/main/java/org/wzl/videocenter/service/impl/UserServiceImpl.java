package org.wzl.videocenter.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.wzl.videocenter._do.User;
import org.wzl.videocenter.exception.BizException;
import org.wzl.videocenter.service.UserService;
import org.wzl.videocenter.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.wzl.videocenter.utils.IdGen;

/**
* @author 卫志龙
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-07-14 17:08:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        commonValid(user);

        User targetUser = getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (null != targetUser) {
            throw new BizException("该用户名不可用");
        }

        user.setId(IdGen.getId());
        save(user);
    }

    @Override
    public User login(User user) {
        commonValid(user);

        User targetUser = getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (null == targetUser) {
            throw new BizException("该用户名不存在");
        }

        if (!user.getPassword().equals(targetUser.getPassword())) {
            throw new BizException("密码错误");
        }

        targetUser.setPassword("*");
        return targetUser;
    }

    private void commonValid(User user) {
        if (ObjectUtils.isEmpty(user.getUsername())) {
            throw new BizException("请输入用户名");
        }
        if (ObjectUtils.isEmpty(user.getPassword())) {
            throw new BizException("请输入密码");
        }
    }
}




