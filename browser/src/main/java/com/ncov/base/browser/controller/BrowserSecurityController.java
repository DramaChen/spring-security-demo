package com.ncov.base.browser.controller;

import com.ncov.base.core.constants.SecurityConstants;
import com.ncov.base.core.entity.JsonResultVO;
import com.ncov.base.core.entity.SocialUserInfo;
import com.ncov.base.core.properties.SystemSecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 *    描述：登录安全路径控制器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/18 8:04
 */
@RestController
public class BrowserSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    /**
     * <pre>
     *      功能描述: 根据请求路径的类型，以Json方式或跳转登录页的方式进行响应
     * </pre>
     * @author ChenJunLin
     * @param request
     * @param response
     * @return com.drama.security.entity.vo.JsonResultVO
     * @date 2020/2/18 8:05
     */
    @GetMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
    )
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)//设置返回的响应码
    public JsonResultVO browserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest saveRequest = requestCache.getRequest(request, response);
        if (saveRequest!=null){
            String redirectUrl = saveRequest.getRedirectUrl();
            logger.info("引发跳转的url:{}",redirectUrl);
            //如果是html的请求，则跳转到指定的登录页面
            if (StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                //跳转到指定的页面
                redirectStrategy.sendRedirect(request,response, systemSecurityProperties.getLogin().getLoginPage());
            }
        }
        return JsonResultVO.failure("访问的服务需要身份验证，请引导用户到登陆页");
    }

    /**
     * <pre>
     *      功能描述: 获取第三方用户信息
     * </pre>
     * @author ChenJunLin
     * @param request
     * @return com.ncov.base.core.entity.SocialUserInfo
     * @date 2020/2/22 17:47
     */
    @GetMapping("/socialUserInfo")
    public SocialUserInfo get(HttpServletRequest request){
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setHeaderImg(connection.getImageUrl());
        return socialUserInfo;
    }

    /**
     * <pre>
     *      功能描述: session失效跳转路径
     * </pre>
     * @author ChenJunLin
     * @param
     * @return com.ncov.base.core.entity.JsonResultVO
     * @date 2020/2/22 17:47
     */
    @GetMapping("/session/invalid")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)//设置返回的响应码
    public JsonResultVO sessionInvalid(){
        return JsonResultVO.error("Session已过期");
    }
}
