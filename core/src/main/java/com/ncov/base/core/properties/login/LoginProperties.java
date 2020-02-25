package com.ncov.base.core.properties.login;

import com.ncov.base.core.constants.SecurityConstants;
import com.ncov.base.core.constants.SecurityLoginType;
import lombok.Data;

/**
 * <pre>
 *    描述：Security安全登录配置
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/19 16:51
 */
@Data
public class LoginProperties {

    /**
     * <pre>
     *     描述：未登录默认跳转登录页
     *     （由SecurityController控制未登录时，跳转默认登录页/返回json数据）
     * </pre>
     */
    private String loginPage = SecurityConstants.LOGIN_PAGE;
    /**
     * <pre>
     *     描述：账号密码登录接口路径
     * </pre>
     */
    private String loginProcessUrl=SecurityConstants.LOGIN_PROCESS_URL;
    /**
     * <pre>
     *     描述：手机验证码登录接口路径
     * </pre>
     */
    private String loginProcessUrlMobile=SecurityConstants.LOGIN_PROCESS_URL_MOBILE;
    /**
     * <pre>
     *     描述：登录成功返回方式：返回用户认证信息or跳转指定页面
     * </pre>
     */
    private SecurityLoginType method= SecurityLoginType.JSON;
    /**
     * <pre>
     *     描述：登录成功跳转页面
     *     (若登录方式为SecurityLoginType.JSON，则返回用户认证数据，
     *     否则跳转到配置的登陆成功跳转页面)
     * </pre>
     */
    private String loginSuccessPage="/";

    /**
     * <pre>
     *     描述：登录失败跳转页面
     *           (若登录方式为SecurityLoginType.JSON，则返回用户认证数据，
     *          否则跳转到配置的登陆失败跳转页面)
     * </pre>
     */
    private String loginFailurePage;

    /**
     * <pre>
     *     描述：注册页面路径
     * </pre>
     */
    private String signInUrl;
    /**
     * <pre>
     *     描述：最大允许相同用户同时在线数
     * </pre>
     */
    private Integer maximumSessions=1;
    /**
     * <pre>
     *     描述：达到最大session时是否阻断新用户登录
     * </pre>
     */
    private Boolean maxSessionsPreventsLogin=false;
    /**
     * <pre>
     *     描述：Session失效跳转路径
     * </pre>
     */
    private String sessionInvalidUrl="/session/invalid";
}
