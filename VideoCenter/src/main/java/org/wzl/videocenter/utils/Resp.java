package org.wzl.videocenter.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: 卫志龙
 * @date: 2024年07月14日 17:10
 */
@Data
@AllArgsConstructor
public class Resp <T> {

    private T data;
    private String msg;
    private Integer code;

    public static <T> Resp<T> success() {
        return new Resp<T>(null, "success", 200);
    }

    public static <T> Resp<T> success(T data) {
        return new Resp<T>(data, "success", null);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<>(null, msg, 410);
    }

}
