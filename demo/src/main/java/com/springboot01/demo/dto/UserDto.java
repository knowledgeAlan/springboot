package com.springboot01.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * create by zzm on 2018/4/15
 **/
@ApiModel("用户请求参数")
public class UserDto {

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "注册开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regStartDate;

    @ApiModelProperty(value = "注册结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regEndDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegStartDate() {
        return regStartDate;
    }

    public void setRegStartDate(Date regStartDate) {
        this.regStartDate = regStartDate;
    }

    public Date getRegEndDate() {
        return regEndDate;
    }

    public void setRegEndDate(Date regEndDate) {
        this.regEndDate = regEndDate;
    }
}
