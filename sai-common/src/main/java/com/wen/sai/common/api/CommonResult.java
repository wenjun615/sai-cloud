package com.wen.sai.common.api;

import lombok.Data;

/**
 * <p>
 * 公共返回结果封装类
 * </p>
 *
 * @author wenjun
 * @since 2021/1/24
 */
@Data
public class CommonResult<T> {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 成功返回结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCodeImpl.SUCCESS.getCode(), ResultCodeImpl.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    响应数据
     * @param message 响应信息
     * @param <T>     响应数据类型
     * @return 成功返回结果
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(ResultCodeImpl.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param resultCode 操作码
     * @param <T>        响应数据类型
     * @return 失败返回结果
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode) {
        return new CommonResult<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param resultCode 操作码
     * @param message    响应信息
     * @param <T>        响应数据类型
     * @return 失败返回结果
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode, String message) {
        return new CommonResult<>(resultCode.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param <T> 响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCodeImpl.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 响应信息
     * @param <T>     响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return failed(ResultCodeImpl.VALIDATE_FAILED, message);
    }

    /**
     * 未登录返回结果
     *
     * @param <T> 响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> unauthenticated() {
        return failed(ResultCodeImpl.UNAUTHENTICATED);
    }

    /**
     * 未登录返回结果
     *
     * @param message 响应信息
     * @param <T>     响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> unauthenticated(String message) {
        return failed(ResultCodeImpl.UNAUTHENTICATED, message);
    }

    /**
     * 权限不足返回结果
     *
     * @param <T> 响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> unauthorized() {
        return failed(ResultCodeImpl.UNAUTHORISED);
    }

    /**
     * 权限不足返回结果
     *
     * @param message 响应信息
     * @param <T>     响应数据类型
     * @return 返回结果
     */
    public static <T> CommonResult<T> unauthorized(String message) {
        return failed(ResultCodeImpl.UNAUTHORISED, message);
    }
}
