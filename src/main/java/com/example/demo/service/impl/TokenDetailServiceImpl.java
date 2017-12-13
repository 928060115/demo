package com.example.demo.service.impl;

import com.example.demo.service.TokenDetailService;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.service.impl
 * @Date: 2017/12/13
 * @Time: 11:36
 */
public class TokenDetailServiceImpl implements TokenDetailService {

    private final String username;

    public TokenDetailServiceImpl(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
