package com.zhbit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhbit.common.Result;
import com.zhbit.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getUserByName(String UserName);

    Map Login(String userName, String password);

    Result addUser (Map<String, String> map, String token);

    Result modifyPassword(Map<String, String> map,String token);

}
