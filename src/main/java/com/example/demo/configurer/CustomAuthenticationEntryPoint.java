package com.example.demo.configurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: rogue
 * @Description: 自定义401错误代码
 * @Package: com.example.demo.configurer
 * @Date: 2017/12/13
 * @Time: 16:53
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("========CustomAuthenticationEntryPoint=============");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
    }
}
