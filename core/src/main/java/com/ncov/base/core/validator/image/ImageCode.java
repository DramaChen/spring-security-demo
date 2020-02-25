package com.ncov.base.core.validator.image;

import com.ncov.base.core.validator.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ImageCode extends ValidateCode {
    private static final long serialVersionUID = 6970561471516181854L;

    public ImageCode(BufferedImage image,String code,Integer expired){
        super(code,expired);
        this.image=image;
    }

    private BufferedImage image;

}
