package org.wzl.videocenter.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * 测试DB Mapper
 *
 * @author: 卫志龙
 * @date: 2023-10-19 09:19
 */
public interface CheckMapper {

    @Select("select 1 limit 1")
    int check();

}
