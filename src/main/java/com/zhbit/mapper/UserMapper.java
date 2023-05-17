package com.zhbit.mapper;

import com.zhbit.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserByName(String UserName);

}
