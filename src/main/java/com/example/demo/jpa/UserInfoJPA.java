package com.example.demo.jpa;

import com.example.demo.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: rogue
 * @Description: 请求用户信息数据接口
 * @Package: com.example.demo.jpa
 * @Date: 2017/12/14
 * @Time: 15:49
 */
public interface UserInfoJPA extends JpaRepository<UserInfoEntity,String>,JpaSpecificationExecutor<UserInfoEntity>{
}
