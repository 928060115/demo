package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    /**
     * @Author: rogue
     * @Description: 初始化登录页面
     * @ClassName: IndexController
     * @Date: 2017/11/30
     * @Time: 11:33
     */
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }

    /**
     * @Author: rogue
     * @Description: 错误登录页面
     * @ClassName: IndexController
     * @Date: 2017/12/12
     * @Time: 15:39
     */
    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError() {
        return "404";
    }

    @RequestMapping(value = "/fileupload", method = RequestMethod.GET)
    public String upload_page() {
        return "fileupload";
    }
}
