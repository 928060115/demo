package com.example.demo.configurer;

import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.configurer
 * @Date: 2017/12/12
 * @Time: 14:10
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //完成自定义实体注入
    @Bean
    UserDetailsService userService() {
        return new UserService();
    }

    //添加自定义的userService认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()//所有请求必须登陆后访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/loginError")
                .permitAll()//登录界面，错误界面可以直接访问
                .and()
                .logout()
                .permitAll();//注销请求可直接访问
    }
}
