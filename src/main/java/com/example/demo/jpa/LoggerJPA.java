package com.example.demo.jpa;

import com.example.demo.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.jpa
 * @Date: 2017/11/30
 * @Time: 17:41
 */
public interface LoggerJPA extends JpaRepository<LoggerEntity,Long> {
}
