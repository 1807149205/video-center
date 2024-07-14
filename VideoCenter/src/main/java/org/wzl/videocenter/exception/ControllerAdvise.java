package org.wzl.videocenter.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wzl.videocenter.utils.Resp;

/**
 * @author: 卫志龙
 * @date: 2024年07月14日 17:38
 */
@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(BizException.class)
    public Resp<?> bizException(BizException e) {
        return Resp.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Resp<?> bizException(Exception e) {
        return Resp.fail("系统错误");
    }


}
