package com.zhbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhbit.common.Result;
import com.zhbit.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService extends IService<User> {

    User getUserByName(String UserName);

    Map Login(String userName, String password);

    Result addUser (Map<String, String> map,String token);

}
