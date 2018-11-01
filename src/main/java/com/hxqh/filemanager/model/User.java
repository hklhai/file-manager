package com.hxqh.filemanager.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ocean lin on 2018/10/30.
 *
 * @author Ocean lin
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 2000427885888306027L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    private String userName;

    @JSONField(serialize = false)
    private String password;

    private String name;

    private int age;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date crateDate;

    public User() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }
}


