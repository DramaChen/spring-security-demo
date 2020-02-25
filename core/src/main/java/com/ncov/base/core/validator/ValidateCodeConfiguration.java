package com.ncov.base.core.validator;

import com.ncov.base.core.properties.SystemSecurityProperties;
import com.ncov.base.core.validator.image.ImageValidateCodeGenerator;
import com.ncov.base.core.validator.sms.DefaultSmsCodeSender;
import com.ncov.base.core.validator.sms.SmsCodeSender;
import com.ncov.base.core.validator.sms.SmsValidateCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *    描述：验证码配置管理器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/24 21:02
 */
@Configuration
public class ValidateCodeConfiguration {

    @Bean
    public ValidateCodeGenerator imageValidateCodeGenerator(SystemSecurityProperties properties){
        return new ImageValidateCodeGenerator(properties);
    }

    @Bean
    public ValidateCodeGenerator smsValidateCodeGenerator(SystemSecurityProperties properties){
        return new SmsValidateCodeGenerator(properties);
    }

    @Bean
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
