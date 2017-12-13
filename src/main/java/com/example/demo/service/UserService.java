package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.service
 * @Date: 2017/12/12
 * @Time: 10:25
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService implements UserDetailsService{
    @Autowired
    private UserJPA userJPA;

    @Cacheable
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userJPA.findByUsername(username);
        if (user == null){
            System.out.println("未查询到用户：" + username + "的信息");
            throw new UsernameNotFoundException("未查询到用户：" + username + "的信息");
        }
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(user.getRoles());
//        return new User(user.getUsername(),user.getPassword(),simpleGrantedAuthorities);
        return user;
    }

    private List<SimpleGrantedAuthority> createAuthorities(List<RoleEntity> roles){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return simpleGrantedAuthorities;
    }
}


