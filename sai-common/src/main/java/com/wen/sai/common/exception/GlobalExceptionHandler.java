package com.wen.sai.common.exception;

import com.wen.sai.common.api.CommonResult;
import com.wen.sai.common.api.ResultCodeImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author wenjun
 * @since 2021/1/25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义 API 异常处理
     *
     * @param e 自定义 API 异常
     * @return 公共返回结果对象
     */
    @ExceptionHandler(ApiException.class)
    public CommonResult apiExceptionHandler(ApiException e) {
        if (e.getResultCode() != null) {
            return CommonResult.failed(e.getResultCode());
        }
        log.error(e.getMessage(), e);
        return CommonResult.failed(ResultCodeImpl.FAILED, e.getMessage());
    }

    /**
     * 方法参数校验失败异常处理
     *
     * @param e 方法参数校验失败异常
     * @return 公共返回结果对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult validExceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder("请求参数校验失败：");
        e.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> stringBuilder.append(fieldError.getField())
                        .append(":")
                        .append(fieldError.getDefaultMessage())
                        .append(";"));
        log.error(e.getMessage(), e);
        return CommonResult.failed(ResultCodeImpl.VALIDATE_FAILED, stringBuilder.toString());
    }
}
