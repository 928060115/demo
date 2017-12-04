package com.example.demo.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.entity
 * @Date: 2017/11/30
 * @Time: 17:07
 */

@Entity
@Table(name = "t_logger_infos")
public class LoggerEntity implements Serializable {

    //编号
    @Id
    @GeneratedValue
    @Column(name = "ali_id")
    private Long id;
    //客户端请求ip
    @Column(name = "ali_client_ip")
    private String clientIp;
    //客户端请求路径
    @Column(name = "ali_uri")
    private String uri;
    //终端请求方式，普通请求/AJAX请求
    @Column(name = "ali_type")
    private String type;
    //请求方式method，GET/POST等
    @Column(name = "ali_method")
    private String method;
    //请求参数内容，json
    @Column(name = "ali_param_data")
    private String paramData;
    //请求接口唯一的session标识
    @Column(name = "ali_session_id")
    private String sessionId;
    //请求时间
    @Column(name = "ali_time",insertable = false)
    private Timestamp time;
    //接口返回时间
    @Column(name = "ali_return_time")
    private String returnTime;
    //接口返回数据，json
    @Column(name = "ali_return_data")
    private String returnData;
    //接口返回状态码
    @Column(name = "ali_http_status_code")
    private String statusCode;
    //请求时间差
    @Column(name = "ali_time_consuming")
    private int timeConsuming;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType(String requestType) {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(int timeConsuming) {
        this.timeConsuming = timeConsuming;
    }
}
