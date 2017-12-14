package com.example.demo.jpa;

import com.example.demo.entity.TokenInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.jpa
 * @Date: 2017/12/14
 * @Time: 15:52
 */
public interface TokenInfoJPA extends JpaRepository<TokenInfoEntity,String> ,JpaSpecificationExecutor<TokenInfoEntity> {
}
