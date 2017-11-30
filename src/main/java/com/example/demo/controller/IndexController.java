package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/11/29
 * @Time: 15:07
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * @Author: rogue
     * @Description: 初始化登录页面
     * @ClassName: IndexController
     * @Date: 2017/11/30
     * @Time: 11:33
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
