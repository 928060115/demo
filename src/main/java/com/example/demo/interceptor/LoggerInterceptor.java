package com.example.demo.interceptor;

import ch.qos.logback.classic.util.LoggerNameUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.LoggerEntity;
import com.example.demo.jpa.LoggerJPA;
import com.example.demo.utils.LoggerUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.configurer
 * @Date: 2017/11/30
 * @Time: 17:44
 */
public class LoggerInterceptor implements HandlerInterceptor {

    //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";
    //请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";

    /**
     * @Author: rogue
     * @Description: 根据传入的类型获取spring管理的对应的DAO
     * @ClassName: LoggerInterceptor
     * @Date: 2017/12/1
     * @Time: 10:41
     */
    private <T> T getDAO(Class<T> clazz, HttpServletRequest request){
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

    /**
     * @Author: rogue
     * @Description: 进入SpringMVC的Controller之前开始记录日志实体
     * @ClassName: LoggerInterceptor
     * @Date: 2017/11/30
     * @Time: 17:54
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //创建日志实体
        LoggerEntity logger = new LoggerEntity();
        //获取请求sessionid
        String sessionId = httpServletRequest.getRequestedSessionId();
        //请求路径
        String uri = httpServletRequest.getRequestURI();
        //获取请求参数信息
        String paramData = JSON.toJSONString(httpServletRequest.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        //设置客户端ip
        logger.setClientIp(LoggerUtil.getClientIp(httpServletRequest));
        //设置请求方法
        logger.setMethod(httpServletRequest.getMethod());
        //设置请求类型（ajax|普通请求）
        logger.getType(LoggerUtil.getRequestType(httpServletRequest));
        //设置请求参数内容json字符串
        logger.setParamData(paramData);
        //设置请求地址
        logger.setUri(uri);
        //设置sessionId
        logger.setSessionId(sessionId);
        //设置请求开始时间
        httpServletRequest.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());
        //设置请求实体到request内，方便afterCompletion方法调用
        httpServletRequest.setAttribute(LOGGER_ENTITY,logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //获取请求错误码
        int status = httpServletResponse.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //请求开始时间
        long time = Long.valueOf(httpServletRequest.getAttribute(LOGGER_SEND_TIME).toString());
        //获取本次请求日志实体
        LoggerEntity loggerEntity = (LoggerEntity) httpServletRequest.getAttribute(LOGGER_ENTITY);
        //设置请求时间差
        loggerEntity.setTimeConsuming(Integer.valueOf((currentTime-time)+""));
        //设置返回时间
        loggerEntity.setReturnTime(currentTime+"");
        //设置返回码
        loggerEntity.setStatusCode(status+"");
        //设置返回值
        loggerEntity.setReturnData(JSON.toJSONString(httpServletRequest.getAttribute(LoggerUtil.LOGGER_RETURN),
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect));
        //执行将日志写入数据库
        LoggerJPA loggerDAO = getDAO(LoggerJPA.class,httpServletRequest);
        loggerDAO.save(loggerEntity);
    }
}
