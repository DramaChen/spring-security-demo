package com.ncov.base.core.validator;

import com.ncov.base.core.constants.SecurityConstants;
import com.ncov.base.core.entity.JsonResultVO;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ValidateCodeController {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeController.class);

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/{type}")
    public void createValidateCode(@PathVariable("type")String type, HttpServletRequest request, HttpServletResponse response){
        try {
            validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
        } catch (Exception e) {
           logger.error("验证处理器处理失败:{}",e);
        }
    }
}
