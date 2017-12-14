package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/12/14
 * @Time: 17:21
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {


    @ApiOperation(value = "测试API方法",notes = "测试API授权")
    @RequestMapping(value = "/testApi")
    public String testApi(){
        return "testApi";
    }
}
