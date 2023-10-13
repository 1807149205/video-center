package org.wzl.videocenter._enum;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 卫志龙
 * @Date: 2023/10/13/17:50
 * @Description:
 */
public enum ResponseEnum {

    OK(200, "成功"),
    NO_PAGE(404, "没有该页面"),
    PARAM_ERROR(400, "参数错误");

    private final Integer code;
    private final String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
