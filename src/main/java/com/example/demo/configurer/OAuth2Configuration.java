package com.example.demo.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.configurer
 * @Date: 2017/12/13
 * @Time: 16:50
 */
@Configuration
public class OAuth2Configuration {
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends  ResourceServerConfigurerAdapter{

        @Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        private CustomLogoutSuccessHandler customLogoutSuccessHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl("/oauth/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/user/**").authenticated();
        }
    }
}
