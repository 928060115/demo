package com.example.demo.entity;

import com.example.demo.validator.DefinedValidator;

import javax.persistence.Entity;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.entity
 * @Date: 2017/12/6
 * @Time: 16:51
 */
public class ValidatorEntity {
    //测试自定义 validator
    @DefinedValidator(values = "1,2,3")
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
