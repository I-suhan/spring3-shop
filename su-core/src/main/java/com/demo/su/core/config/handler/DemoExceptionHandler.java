package com.demo.su.core.config.handler;

import com.demo.su.core.common.dto.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DemoExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Result<?> handleBizException(HttpServletRequest request, HttpServletResponse response, BizException e){
        log(request, e);
        String code = e.getErrorCode().getCode();
        HttpStatus httpStatus = HttpStatus.valueOf(e.getStatusCode());
        response.setStatus(httpStatus.value());
        return new Result<>(Integer.valueOf(code), e.getMessage());
    }

    public void log(HttpServletRequest request, Exception exception) {
        //换行符
        String lineSeparatorStr = System.getProperty("line.separator");

        StringBuilder exStr = new StringBuilder();
        StackTraceElement[] trace = exception.getStackTrace();
        // 获取堆栈信息并输出为打印的形式
        for (StackTraceElement s : trace) {
            exStr.append("\tat ").append(s).append("\r\n");
        }
        //打印error级别的堆栈日志
        log.error("访问地址：" + request.getRequestURL() + ",请求方法：" + request.getMethod() +
                ",远程地址：" + request.getRemoteAddr() + lineSeparatorStr +
                "错误堆栈信息如下:" + exception + lineSeparatorStr + exStr);
    }
}
