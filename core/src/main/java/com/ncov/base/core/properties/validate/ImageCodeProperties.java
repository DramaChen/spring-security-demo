package com.ncov.base.core.properties.validate;

import lombok.Data;

@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties(){
        setLength(4);
    }

    //验证码图片高度
    private int height=23;

    //验证码图片宽度
    private int width=67;

    //线条复杂度
    private int lineCount=50;

    //验证码规则字符生成范围
    private String baseStr="1234567890";
}
