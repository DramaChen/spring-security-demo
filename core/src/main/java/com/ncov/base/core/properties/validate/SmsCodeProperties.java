package com.ncov.base.core.properties.validate;

import lombok.Data;

@Data
public class SmsCodeProperties {

    private Integer expireIn = 60;

    private String url;

    private Integer length=6;
}
