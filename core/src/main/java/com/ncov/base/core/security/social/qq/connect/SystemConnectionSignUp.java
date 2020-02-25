package com.ncov.base.core.security.social.qq.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class SystemConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息创建用户，并返回用户唯一标识
        //DEMO
        return "id";//
    }
}
