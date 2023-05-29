package com.zhbit.controller;

import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.pojo.User;
import com.zhbit.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.zhbit.common.StatusCode.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public Map login(String userName, String password, HttpServletResponse response) {
        Map login = userService.Login(userName, password);
        if (login.get("statusCode").equals(LOGIN_OK)) {
            response.setHeader("token",(String) login.get("token"));
        }
        return login;
    }

    @PutMapping("/user/modifyPassword")
    public Result modifyPassword(@RequestBody Map<String, String> map) {
        User user = new User();
        user.setId(Integer.parseInt(map.get("id")));
        user.setPassword(map.get("password"));
        boolean flag = userService.updateById(user);
        String msg = "update success";
        return new Result(flag ? StatusCode.SAVE_OK : StatusCode.SAVE_ERR, msg);
    }

    @PostMapping("/user/addUser")
    public Result addUser(@RequestBody Map<String, String> map, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return userService.addUser(map,token);
    }
}