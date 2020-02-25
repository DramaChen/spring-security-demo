package com.ncov.base.core.properties;

import com.ncov.base.core.properties.login.LoginProperties;
import com.ncov.base.core.properties.social.SocialSecurityProperties;
import com.ncov.base.core.properties.validate.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "drama.security")
@Data
public class SystemSecurityProperties {

    private LoginProperties login = new LoginProperties();

    private SocialSecurityProperties social = new SocialSecurityProperties();

    private ValidateCodeProperties validate = new ValidateCodeProperties();
}
