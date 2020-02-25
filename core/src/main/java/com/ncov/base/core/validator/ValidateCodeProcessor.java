package com.ncov.base.core.validator;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <pre>
 *    描述：验证码处理生成流程处理器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/23 20:30
 */
public interface ValidateCodeProcessor {

    /**
     * <pre>
     *     描述：验证码生成
     * </pre>
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * <pre>
     *     描述：验证码校验
     * </pre>
     */
    void validate(ServletWebRequest request);
}
