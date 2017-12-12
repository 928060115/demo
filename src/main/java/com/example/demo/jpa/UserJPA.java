package com.example.demo.jpa;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.jpa
 * @Date: 2017/11/29
 * @Time: 15:43
 */
public interface UserJPA extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>, Serializable {

    //自定义查询
    UserEntity findByUsername(String username);

    //自定义数据库查询语句
    //查询性别为男的user信息
    @Query(value = "select * from user where sex=?1" ,nativeQuery = true)
    List<UserEntity> nativeQuery(int sex);

    //配合Modifying可以完成增加，删除和修改
    @Transactional
    @Modifying
    @Query(value = "delete from user where username=?1 and password=?2",nativeQuery = true)
    void deleteByUsername(String username, String password);
}
