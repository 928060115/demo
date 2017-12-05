package com.example.demo.jpa;

import com.example.demo.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.jpa
 * @Date: 2017/12/5
 * @Time: 15:53
 */
//集成QueryDslPredicateExecutor接口，该接口是SpringDataJPA提供的querydsl查询接口
public interface GoodJPA extends JpaRepository<GoodEntity,Long>,QueryDslPredicateExecutor<GoodEntity> {
}
