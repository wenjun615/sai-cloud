package com.wen.sai.common.api;

/**
 * <p>
 * 操作码接口
 * </p>
 *
 * @author wenjun
 * @since 2021/1/23
 */
public interface ResultCode {

    /**
     * 获取操作码
     *
     * @return 操作码
     */
    String getCode();

    /**
     * 获取操作码信息
     *
     * @return 操作码信息
     */
    String getMessage();
}
