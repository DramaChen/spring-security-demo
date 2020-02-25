package com.ncov.base.core.validator;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * <pre>
 *    描述：验证码生成
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/23 22:30
 */
public interface ValidateCodeGenerator {

    /**
     * <pre>
     *      功能描述: 生成
     * </pre>
     * @author ChenJunLin
     * @param request
     * @return com.ncov.base.core.validator.ValidateCode
     * @date 2020/2/24 0:39
     */
    ValidateCode generate(ServletWebRequest request);
}
