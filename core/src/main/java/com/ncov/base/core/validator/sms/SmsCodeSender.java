package com.ncov.base.core.validator.sms;

/**
 * <pre>
 *    描述：短信验证码发送器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/24 9:07
 */
public interface SmsCodeSender {

   void send(String mobild,String code);
}
