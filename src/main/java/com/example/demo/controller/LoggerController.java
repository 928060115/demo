package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.LoggerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/12/1
 * @Time: 10:44
 */
@RestController
@RequestMapping("/logger")
public class LoggerController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public JSONObject index(HttpServletRequest request,String name){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","用户"+name+",登录成功");
        //将返回值写入到请求对象中
        request.setAttribute(LoggerUtil.LOGGER_RETURN,jsonObject);
        return jsonObject;
    }
}
