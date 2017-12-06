package com.example.demo.controller;

import com.example.demo.entity.ValidatorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/12/6
 * @Time: 16:53
 */
@RestController
@RequestMapping(value = "/validator")
public class ValidatorController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/flag",method = RequestMethod.GET)
    public String testFlag(@Valid ValidatorEntity validator, BindingResult result){
        if (result.hasErrors()){
            StringBuffer msg = new StringBuffer();
            //获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            //获取本地locale，zh_CN
            Locale currentLocale = LocaleContextHolder.getLocale();
            //遍历错误字段获取错误信息
            for (FieldError fieldError : fieldErrors){
                //获取错误信息
                String errorMsg = messageSource.getMessage(fieldError,currentLocale);
                //添加到错误消息集合内
                msg.append(fieldError.getField()+":"+errorMsg+",");
            }
            return msg.toString();
        }
        return "验证通过，"+"名称："+validator.getFlag();
    }
}
