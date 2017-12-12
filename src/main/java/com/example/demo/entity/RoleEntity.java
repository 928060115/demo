package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.entity
 * @Date: 2017/12/12
 * @Time: 13:31
 */
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable{
    @Id
    @Column(name = "r_id")
    private Long id;

    @Column(name = "r_name")
    private String rname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
