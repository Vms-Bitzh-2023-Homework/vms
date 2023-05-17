package com.zhbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhbit.pojo.User;

public interface UserService extends IService<User> {

    User getUserByName(String UserName);

}
