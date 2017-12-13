package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.UserOauthEntity;
import com.example.demo.jpa.UserOauthJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.service
 * @Date: 2017/12/13
 * @Time: 16:29
 */
@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private UserOauthJPA userOauthJPA;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        String lowercaseLogin = login.toLowerCase();

        UserOauthEntity userFromDatabase = userOauthJPA.findByUsernameCaseInsensitive(lowercaseLogin);

        if (userFromDatabase == null){
            throw new UsernameNotFoundException("UserOauth" + lowercaseLogin + "was not found in the databse");
        }

        //获取用户所有权限并且springSecurity需要的集合
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        //返回一个SpringSecurity需要的对象
        return new User(userFromDatabase.getUsername(),userFromDatabase.getPassword(),grantedAuthorities);
    }
}
