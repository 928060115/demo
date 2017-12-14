package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.entity
 * @Date: 2017/12/14
 * @Time: 15:46
 */
@Entity
@Table(name = "api_token_infos",schema = "jwt")
public class TokenInfoEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ati_id")
    private Long id;

    @Column(name = "ati_app_id")
    private String appId;

    @Column(name = "ati_token")
    private byte[] token;

    @Column(name = "ati_build_time")
    private String buildTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }
}
