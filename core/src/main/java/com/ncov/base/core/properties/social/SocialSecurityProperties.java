package com.ncov.base.core.properties.social;

import lombok.Data;

@Data
public class SocialSecurityProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl;
}
