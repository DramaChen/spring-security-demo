package com.ncov.base.core.security.logout;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ncov.base.core.entity.JsonResultVO;
import com.ncov.base.core.properties.SystemSecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 *    描述：登出成功处理器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/25 23:13
 */
@Component
public class SystemLogoutSuccessHandler implements LogoutSuccessHandler {

    private SystemSecurityProperties properties;

    public SystemLogoutSuccessHandler(SystemSecurityProperties properties){
        this.properties=properties;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String logoutPage = properties.getLogin().getLogoutPage();
        if (StringUtils.isBlank(logoutPage)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr(JsonResultVO.success("登出成功")));
        }else {
            response.sendRedirect(logoutPage);
        }
    }
}
