package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.entity
 * @Date: 2017/12/14
 * @Time: 15:40
 */
@Entity
@Table(name = "api_user_infos",schema = "jwt")
public class UserInfoEntity implements Serializable{

    @Id
    @Column(name = "aui_app_id")
    private String appId;

    @Column(name = "aui_app_secret")
    private byte[] appSecret;

    @Column(name = "aui_status")
    private int status;

    @Column(name = "aui_day_request_count")
    private int dayRequestCount;

    @Column(name = "aui_ajax_bind_ip")
    private String apiBindIp;

    @Column(name = "aui_mark")
    private String mark;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public byte[] getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(byte[] appSecret) {
        this.appSecret = appSecret;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDayRequestCount() {
        return dayRequestCount;
    }

    public void setDayRequestCount(int dayRequestCount) {
        this.dayRequestCount = dayRequestCount;
    }

    public String getApiBindIp() {
        return apiBindIp;
    }

    public void setApiBindIp(String apiBindIp) {
        this.apiBindIp = apiBindIp;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
