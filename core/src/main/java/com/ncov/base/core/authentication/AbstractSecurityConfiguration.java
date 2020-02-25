package com.ncov.base.core.authentication;

import com.ncov.base.core.constants.SecurityConstants;
import com.ncov.base.core.properties.SystemSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AbstractSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    protected AuthenticationSuccessHandler browserAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler browserAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.LOGIN_PROCESS_URL)
                .successHandler(browserAuthenticationSuccessHandler)
                .failureHandler(browserAuthenticationFailureHandler);
    }
}
