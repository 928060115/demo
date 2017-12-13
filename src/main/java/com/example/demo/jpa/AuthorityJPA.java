package com.example.demo.jpa;

import com.example.demo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.jpa
 * @Date: 2017/12/13
 * @Time: 16:27
 */
public interface AuthorityJPA extends JpaRepository<Authority,String>{
}
