package com.ncov.base.web.security;

import com.ncov.base.core.security.model.SystemUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

//使该服务类作为组件
@Component
public class SystemUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buildUserDetail(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return buildUserDetail(userId);
    }

    public SocialUserDetails buildUserDetail(String username){
        //1. 创建一个用户信息用来模拟保存一个新用户信息
        //对用户保存的密码进行加密后再保存到数据库
        String password=passwordEncoder.encode("123456");
        SystemUserDetail userDetail = new SystemUserDetail("1", username, password, true, true, false, true);
        //2. 保存用户信息后，再从数据库获取用户信息出来，并返回
        return new SocialUser(username,password,userDetail.getAuthorities());
    }
}
