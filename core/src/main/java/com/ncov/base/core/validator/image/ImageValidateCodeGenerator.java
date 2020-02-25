package com.ncov.base.core.validator.image;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.ncov.base.core.properties.SystemSecurityProperties;
import com.ncov.base.core.validator.impl.AbstractValidateCodeGenerator;
import com.ncov.base.core.validator.ValidateCode;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * <pre>
 *    描述：图片验证码生成器
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 * @date 2020/2/23 22:32
 */
@Data
public class ImageValidateCodeGenerator extends AbstractValidateCodeGenerator {


    public ImageValidateCodeGenerator(SystemSecurityProperties properties){
        super(properties);
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = generateCode();
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
                properties.getValidate().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
                properties.getValidate().getImage().getHeight());
        RandomGenerator randomGenerator  = new RandomGenerator(properties.getValidate().getImage().getBaseStr(), properties.getValidate().getImage().getLength());
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(width,height,properties.getValidate().getImage().getLength(),properties.getValidate().getImage().getLineCount());
        lineCaptcha.createImage(code);
        lineCaptcha.setGenerator(randomGenerator);
        lineCaptcha.createCode();
        BufferedImage image = lineCaptcha.getImage();
        return new ImageCode(image,lineCaptcha.getCode(),properties.getValidate().getImage().getExpireIn());
    }

    @Override
    protected String generateCode() {
        RandomGenerator randomGenerator  = new RandomGenerator(properties.getValidate().getImage().getBaseStr(), properties.getValidate().getImage().getLength());
        return randomGenerator.generate();
    }
}
