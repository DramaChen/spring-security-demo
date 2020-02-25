package com.ncov.base.core.validator.impl;

import com.ncov.base.core.properties.SystemSecurityProperties;
import com.ncov.base.core.validator.ValidateCodeGenerator;
import lombok.Data;

@Data
public abstract class AbstractValidateCodeGenerator implements ValidateCodeGenerator {


    protected SystemSecurityProperties properties;


    public AbstractValidateCodeGenerator(SystemSecurityProperties properties) {
        this.properties=properties;
    }

    /**
     * <pre>
     *      功能描述: 验证码生成规则，可重写该方法编写验证码生成规则
     * </pre>
     * @author ChenJunLin
     * @param
     * @return C
     * @date 2020/2/24 0:17
     */
    protected abstract String generateCode();
}
