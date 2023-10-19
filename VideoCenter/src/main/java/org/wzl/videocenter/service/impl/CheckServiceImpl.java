package org.wzl.videocenter.service.impl;

import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.wzl.videocenter.mapper.CheckMapper;
import org.wzl.videocenter.service.CheckService;

import javax.annotation.Resource;

/**
 * 测试接口实现类
 *
 * @author: 卫志龙
 * @date: 2023-10-19 09:19
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Resource
    private CheckMapper checkMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int checkDb() {
        return checkMapper.check();
    }

    @Override
    public String checkRedis() {
        return stringRedisTemplate.execute(RedisConnectionCommands::ping);
    }
}
