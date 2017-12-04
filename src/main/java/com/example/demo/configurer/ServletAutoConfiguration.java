package com.example.demo.configurer;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.configurer
 * @Date: 2017/12/1
 * @Time: 14:25
 */
@Configuration
@ServletComponentScan(value = "com.example.demo.servlet")
public class ServletAutoConfiguration {
}
