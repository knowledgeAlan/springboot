package com.springboot01.demo.service;

import com.springboot01.demo.common.PageRequest;
import com.springboot01.demo.common.PageResponse;
import com.springboot01.demo.dto.UserDto;
import com.springboot01.demo.entity.User;

/**
 * create by zzm on 2018/4/16
 **/
public interface UserService {
    /**
     * 列表查询
     * @param pageRequest
     * @param userDto
     * @return
     */
    PageResponse<User> selectPage(PageRequest pageRequest, UserDto userDto);

    /**
     * 用户详情
     * @param id
     * @return
     */
    User detail (Integer id);
}
