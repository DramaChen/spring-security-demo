package com.ncov.base.browser.authentication;

import cn.hutool.json.JSONUtil;
import com.ncov.base.core.constants.SecurityLoginType;
import com.ncov.base.core.entity.JsonResultVO;
import com.ncov.base.core.properties.SystemSecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 *    描述：登录成功处理器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/18 8:27
 */
@Component("browserAuthenticationSuccessHandler")
public class BrowserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private SystemSecurityProperties systemSecurityProperties;

    public BrowserAuthenticationSuccessHandler(SystemSecurityProperties systemSecurityProperties){
        //配置登陆成功页面
        this.systemSecurityProperties=systemSecurityProperties;
        setDefaultTargetUrl(systemSecurityProperties.getLogin().getLoginSuccessPage());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (SecurityLoginType.JSON.equals(systemSecurityProperties.getLogin().getMethod())){
            //以json形式返回用户认证信息
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(JSONUtil.toJsonStr(JsonResultVO.success("登录成功",authentication)));
        }else {
            //跳转到登陆成功页面
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
