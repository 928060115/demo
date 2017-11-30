package com.example.demo.utils;


/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.utils
 * @Date: 2017/11/30
 * @Time: 14:13
 */
public enum MailContentTypeEnum {
    HTML("text/html;charset=UTF-8"),//html格式
    TEXT("text");

    private String value;

    MailContentTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
