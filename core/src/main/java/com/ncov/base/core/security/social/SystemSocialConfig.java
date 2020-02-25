package com.ncov.base.core.security.social;


import com.ncov.base.core.properties.SystemSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SystemSocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired(required = false)//非必须
    private ConnectionSignUp connectionSignUp;


    /**
     * <pre>
     *     描述：配置UsersConnectionRepository
 *              1.可配置加密器
 *              2.可指定UserConnection表的命名规则
     * </pre>
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
//        repository.setTablePrefix("imooc_");
        //配置自定义创建绑定用户逻辑
        if(connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean
    public SpringSocialConfigurer systemSpringSocialConfigurer() {
        String filterProcessesUrl = systemSecurityProperties.getSocial().getFilterProcessesUrl();
        SystemSpringSocialConfigurer configurer = new SystemSpringSocialConfigurer(filterProcessesUrl);
        //配置注册页面
        configurer.signupUrl(systemSecurityProperties.getLogin().getSignInUrl());
        return configurer;
    }

    /**
     * <pre>
     *     描述：工具类：获取授权认证后的用户信息
     * </pre>
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }
}
