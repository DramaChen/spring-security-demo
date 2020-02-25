package com.ncov.base.core.validator.sms;

import cn.hutool.core.util.RandomUtil;
import com.ncov.base.core.properties.SystemSecurityProperties;
import com.ncov.base.core.validator.impl.AbstractValidateCodeGenerator;
import com.ncov.base.core.validator.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public class SmsValidateCodeGenerator extends AbstractValidateCodeGenerator {

    public SmsValidateCodeGenerator(SystemSecurityProperties properties) {
        super(properties);
    }

    @Override
    protected String generateCode() {
        return RandomUtil.randomNumbers(properties.getValidate().getSms().getLength());
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = generateCode();
        ValidateCode validateCode = new ValidateCode(code, properties.getValidate().getSms().getExpireIn());
        return validateCode;
    }
}
