package com.ncov.base.core.constants;

public interface SecurityConstants {

    /**
     * <pre>
     *     描述：默认登录跳转页面
     * </pre>
     */
    String LOGIN_PAGE="/loginPage.html";
    /**
     * <pre>
     *     描述：账号密码登录表单提交路径
     * </pre>
     */
    String LOGIN_PROCESS_URL="/authentication/form";
    /**
     * <pre>
     *     描述：手机登录提交路径
     * </pre>
     */
    String LOGIN_PROCESS_URL_MOBILE="/authentication/mobile";
    /**
     * <pre>
     *     描述：当请求需要身份认证时，默认跳转url
     * </pre>
     */
    String DEFAULT_UNAUTHENTICATION_URL="/security/require";

    /**
     * <pre>
     *     描述：验证码类型
     * </pre>
     */
    //表单短信验证码字段名
    String DEFAULT_PARAMETER_NAME_CODE_SMS="sms";
    //表单图片验证码字段名
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE="image";
    //表单手机号码字段名
    String DEFAULT_PARAMETER_NAME_MOBILE="mobile";
    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * <pre>
     *     描述：默认登出跳转链接
     * </pre>
     */
    String DEFAULT_LOGIN_OUT_URL="/authentication/logout";


}
