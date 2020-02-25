package com.ncov.base.core.security.social.qq.config;

import com.ncov.base.core.properties.SystemSecurityProperties;
import com.ncov.base.core.properties.social.QQProperties;
import com.ncov.base.core.security.social.qq.connect.QQOAuthConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "drama.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = systemSecurityProperties.getSocial().getQq();
        return new QQOAuthConnectionFactory(qqConfig.getProviderId(),qqConfig.getAppId(),qqConfig.getAppSecret());
    }
}
