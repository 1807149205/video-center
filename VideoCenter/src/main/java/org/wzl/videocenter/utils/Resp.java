package org.wzl.videocenter.utils;

import lombok.Data;
import org.wzl.videocenter._enum.ResponseEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 卫志龙
 * @Date: 2023/10/13/17:48
 * @Description:
 */
@Data
public class Resp<T> {

    T data;
    Integer code;
    String msg;

    public Resp(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Resp<T> ok() {
        return new Resp<>(null, ResponseEnum.OK.getCode(), ResponseEnum.OK.getMsg());
    }

    public static <T> Resp<T> ok(T data) {
        return new Resp<>(data, ResponseEnum.OK.getCode(), ResponseEnum.OK.getMsg());
    }

    public static <T> Resp<T> ok(String msg) {
        return new Resp<>(null, ResponseEnum.OK.getCode(), msg);
    }

    public static <T> Resp<T> ok(T data, String msg) {
        return new Resp<>(data, ResponseEnum.OK.getCode(), msg);
    }

   public static <T> Resp<T> fail(ResponseEnum responseEnum) {
        return new Resp<>(null, responseEnum.getCode(), responseEnum.getMsg());
   }

}
