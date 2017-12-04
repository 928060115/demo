package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.utils
 * @Date: 2017/11/30
 * @Time: 18:01
 */
public final class LoggerUtil {
    public static final String LOGGER_RETURN = "_logger_return";

    private LoggerUtil(){};

    /**
     * @Author: rogue
     * @Description: 获取请求ip
     * @ClassName: LoggerUtil
     * @Date: 2017/11/30
     * @Time: 18:09
     */
    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-Ip");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-Ip");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        //多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr){
            if (!"unknown".equalsIgnoreCase(str)){
                ip = str;
                break;
            }
        }
        return ip;
    }

    /**
     * @Author: rogue
     * @Description: 判断请求是否为ajax
     * @ClassName: LoggerUtil
     * @Date: 2017/11/30
     * @Time: 18:15
     */
    public static String getRequestType(HttpServletRequest request){
        return request.getHeader("X-Requested-with");
    }

}
