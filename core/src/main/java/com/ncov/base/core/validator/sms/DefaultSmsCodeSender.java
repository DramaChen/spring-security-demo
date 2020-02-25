package com.ncov.base.core.validator.sms;

/**
 * <pre>
 *    描述：默认短信验证码发送器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/24 9:08
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("默认短信验证码发送器发送消息:"+mobile+",验证码:"+code);
    }
}
