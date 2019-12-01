package com.bowen.common.handler;

import com.bowen.common.utils.Result;
import com.bowen.common.utils.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: changgou
 * @Package: com.bowen.common.handler
 * @ClassName: BaseExceptionHandler
 * @Author: Bowen
 * @Description: 全局异常
 * @Date: 2019/12/1 19:55
 * @Version: 1.0.0
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result result(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());

    }
}
