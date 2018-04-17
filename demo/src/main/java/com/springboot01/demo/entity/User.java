package com.springboot01.demo.entity;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 用户ID
     */
    @Id
    private Integer id;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户身份
     */
    private String role;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 注册时间
     */
    @Column(name = "regTime")
    private Date regtime;

    /**
     * 注册IP
     */
    @Column(name = "regIp")
    private String regip;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户邮箱
     *
     * @return email - 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户昵称
     *
     * @return username - 用户昵称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户昵称
     *
     * @param username 用户昵称
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取用户身份
     *
     * @return role - 用户身份
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置用户身份
     *
     * @param role 用户身份
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取注册时间
     *
     * @return regTime - 注册时间
     */
    public Date getRegtime() {
        return regtime;
    }

    /**
     * 设置注册时间
     *
     * @param regtime 注册时间
     */
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    /**
     * 获取注册IP
     *
     * @return regIp - 注册IP
     */
    public String getRegip() {
        return regip;
    }

    /**
     * 设置注册IP
     *
     * @param regip 注册IP
     */
    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }
}