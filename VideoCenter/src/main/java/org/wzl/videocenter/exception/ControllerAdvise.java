package org.wzl.videocenter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wzl.videocenter.utils.Resp;

/**
 * @author: 卫志龙
 * @date: 2024年07月14日 17:38
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(BizException.class)
    public Resp<?> bizException(BizException e) {
        return Resp.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Resp<?> bizException(Exception e) {
        log.error("系统错误",e);
        return Resp.fail("系统错误");
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public Resp<?> httpMessageNotWritableException(HttpMessageNotWritableException e) {
        return Resp.fail(e.getMessage());
    }


}
