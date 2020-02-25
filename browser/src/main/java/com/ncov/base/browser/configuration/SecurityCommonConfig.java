package com.ncov.base.browser.configuration;

import com.ncov.base.browser.session.BrowserExpiredSessionStrategy;
import com.ncov.base.browser.session.BrowserInvalidSessionStrategy;
import com.ncov.base.core.properties.SystemSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class SecurityCommonConfig {

    @Autowired
    private SystemSecurityProperties properties;

    @Bean
    public  SessionInformationExpiredStrategy informationExpiredStrategy(){
        return new BrowserExpiredSessionStrategy(properties.getLogin().getSessionInvalidUrl());
    };

    @Bean
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new BrowserInvalidSessionStrategy(properties.getLogin().getSessionInvalidUrl());
    };
}
