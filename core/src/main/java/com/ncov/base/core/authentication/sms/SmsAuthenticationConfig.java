package com.ncov.base.core.authentication.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SmsAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private AuthenticationSuccessHandler browserAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * <pre>
     *      功能描述: 主要配置内容
     *      1.初始化Sms相关的 Provider、Filter
     *      2.对http进行配置，设置拦截器位置、加入provider
     * </pre>
     * @author ChenJunLin
     * @param http
     * @return void
     * @date 2020/2/25 8:16
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置拦截器的 AuthenManager、FaliureHandler、SuccessHandler
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(browserAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(browserAuthenticationFailureHandler);
        //配置Provider
        SmsAuthenticaionProvider smsAuthenticaionProvider = new SmsAuthenticaionProvider();
        smsAuthenticaionProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsAuthenticaionProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
