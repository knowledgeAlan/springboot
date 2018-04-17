package com.springboot01.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot01.demo.common.PageRequest;
import com.springboot01.demo.common.PageResponse;
import com.springboot01.demo.dao.UserMapper;
import com.springboot01.demo.dto.UserDto;
import com.springboot01.demo.entity.User;
import com.springboot01.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

/**
 * create by zzm on 2018/4/16
 **/
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Override
    public PageResponse<User> selectPage(PageRequest pageRequest, UserDto userDto) {
        logger.info("查询开始==");
        PageHelper.startPage(pageRequest.getOffset(),pageRequest.getRow());

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(userDto.getRegStartDate())){
              criteria.andGreaterThanOrEqualTo("regtime",userDto.getRegStartDate());
        }
        if (Objects.nonNull(userDto.getRegEndDate())){
            criteria.andLessThanOrEqualTo("regtime",userDto.getRegEndDate());
        }
        List<User> list = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        PageResponse<User> response = new PageResponse<>();
        response.setRows(pageInfo.getList());
        response.setTotal((int)pageInfo.getTotal());
        return response;
    }

    @Override
    public User detail(Integer id) {
        logger.info("详情开始===");
        return userMapper.selectByPrimaryKey(id);
    }
}
