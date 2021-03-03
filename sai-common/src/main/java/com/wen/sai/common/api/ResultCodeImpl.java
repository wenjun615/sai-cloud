package com.wen.sai.common.api;

/**
 * <p>
 * 操作码枚举
 * </p>
 *
 * @author wenjun
 * @since 2021/1/24
 */
public enum ResultCodeImpl implements ResultCode {

    // 操作码
    SUCCESS("200", "操作成功！"),
    FAILED("500", "操作失败！"),
    VALIDATE_FAILED("404", "请求参数验证失败！"),
    UNAUTHENTICATED("401", "未登录或登录过期！"),
    UNAUTHORISED("403", "权限不足！");

    /**
     * 操作码
     */
    private final String code;

    /**
     * 操作信息
     */
    private final String message;

    ResultCodeImpl(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
