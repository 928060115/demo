package com.example.demo.jpa;

import com.example.demo.entity.UserOauthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.jpa
 * @Date: 2017/12/13
 * @Time: 16:22
 */
public interface UserOauthJPA extends JpaRepository<UserOauthEntity,String> {
    @Query("SELECT u FROM UserOauthEntity u WHERE LOWER(u.username) = LOWER(:username)")
    UserOauthEntity findByUsernameCaseInsensitive(@Param("username") String username);
}
