package com.example.demo.service;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.service
 * @Date: 2017/12/13
 * @Time: 11:35
 */
public interface TokenDetailService {
    //这里封装了一层，不直接使用 username 做参数的原因是可以方便未来增加其他要封装到 token 中的信息
    String getUsername();
}
