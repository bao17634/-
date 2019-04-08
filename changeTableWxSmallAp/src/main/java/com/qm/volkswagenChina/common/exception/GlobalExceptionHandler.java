package com.qm.volkswagenChina.common.exception;

import com.qm.yqwl.core.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WangJiuLing on 2017/11/03.
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Integer> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("捕获IllegalArgumentException: {}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = QmGlobalExceptException.class)
    public Result<String> alphaLawyerExceptionHandler(QmGlobalExceptException e) {
        log.error("捕获AlphaLawyerException: {}", e.getMessage());
        return Result.fail("操作失败");
    }
}
