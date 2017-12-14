package com.example.demo.utils;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.utils
 * @Date: 2017/12/14
 * @Time: 15:56
 */
public class TokenResult implements Serializable{
    //状态
    private Boolean flag = true;

    //返回消息内容
    private String msg = "";

    //返回token值
    private String token = "";

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
