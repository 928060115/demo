package com.example.demo.configurer;

import com.example.demo.servlet.MyServlet1;
import com.example.demo.servlet.Test01Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rogue
 * @Description: 手动配置servlet
 * @Package: com.example.demo.configurer
 * @Date: 2017/12/1
 * @Time: 13:33
 */
@Configuration
public class ServletManualConfiguration {

    /**
     * @Author: rogue
     * @Description: 手动装配servlet
     * @ClassName: ServletConfiguration
     * @Date: 2017/12/1
     * @Time: 13:48
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean1() {
        return new ServletRegistrationBean(new MyServlet1(), "/servlet/myServlet1");
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean2(){
        return new ServletRegistrationBean(new Test01Servlet(),"/test01");
    }
}
