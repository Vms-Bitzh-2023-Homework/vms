package com.zhbit.service.Impl;

import com.zhbit.mapper.UserMapper;
import com.zhbit.pojo.User;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String UserName) {
        return userMapper.getUserByName(UserName);
    }
}
