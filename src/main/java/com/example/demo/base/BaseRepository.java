package com.example.demo.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author: rogue
 * @Description:   自定义基础JPARepository
 * @Package: com.example.demo.base
 * @Date: 2017/12/5
 * @Time: 14:36
 */
@NoRepositoryBean //这个注解如果配置在继承了JpaRepository接口以及其他SpringDataJpa内部的接口的子接口时，子接口不被作为一个Repository创建代理实现类。
public interface BaseRepository<T,PK extends Serializable> extends JpaRepository<T,PK> {
}
