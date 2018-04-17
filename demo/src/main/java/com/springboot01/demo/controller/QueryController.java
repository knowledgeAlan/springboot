package com.springboot01.demo.controller;

import com.springboot01.demo.common.PageRequest;
import com.springboot01.demo.common.PageResponse;
import com.springboot01.demo.dto.UserDto;
import com.springboot01.demo.entity.User;
import com.springboot01.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by zzm on 2018/4/15
 **/
@Api(tags = "用户管理")
@RestController
public class QueryController {
    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("查询")
    @GetMapping(value = "/query/user")
    public UserDto query(){
        UserDto userDto = new UserDto();
        userDto.setUserName("张三");
        userDto.setAge(12);
        return userDto;
    }

    @ApiOperation("列表")
    @GetMapping(value = "/query/page")
    public PageResponse<User> page(PageRequest pageRequest,UserDto userDto){
        return userService.selectPage(pageRequest,userDto);
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = "/detail/{id}")
    public User detail(@ApiParam(value = "主键id") @PathVariable(value = "id") Integer id){
        return userService.detail(id);
    }

}
