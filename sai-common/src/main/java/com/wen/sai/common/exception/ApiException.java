package com.wen.sai.common.exception;

import com.wen.sai.common.api.ResultCode;
import lombok.Data;

/**
 * <p>
 * 自定义 API 异常
 * </p>
 *
 * @author wenjun
 * @since 2021/1/25
 */
@Data
public class ApiException extends RuntimeException {

    /**
     * 操作码
     */
    private ResultCode resultCode;

    public ApiException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ApiException(String message) {
        super(message);
    }
}
