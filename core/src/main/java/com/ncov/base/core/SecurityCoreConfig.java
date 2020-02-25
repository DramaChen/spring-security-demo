package com.ncov.base.core;

import com.ncov.base.core.properties.SystemSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SystemSecurityProperties.class)
public class SecurityCoreConfig {


}
