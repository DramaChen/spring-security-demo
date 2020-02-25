package com.ncov.base.core.authentication.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <pre>
 *    描述：手机短信登录方式Provider
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/25 7:35
 */
public class SmsAuthenticaionProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;

    /**
     * <pre>
     *      功能描述: 调用UserDetailsService获取用户信息，并重新封装AuthenticationToken
     * </pre>
     * @author ChenJunLin
     * @param authentication 第一次调用AuthenticationToken构造方法产生的token
     * @return org.springframework.security.core.Authentication
     * @date 2020/2/25 7:40
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        if (userDetails==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsAuthenticationToken smsAuthenticationTokenResult = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        smsAuthenticationTokenResult.setDetails(smsAuthenticationToken.getDetails());
        return smsAuthenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
