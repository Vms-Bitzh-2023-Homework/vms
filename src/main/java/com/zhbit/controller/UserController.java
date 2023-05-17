package com.zhbit.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.zhbit.common.Result;
import com.zhbit.pojo.User;
import com.zhbit.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.zhbit.common.StatusCode.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @Value("${jwt.secret_key}")
    private String secret_key;

    @Value("${jwt.exp_time}")
    private int expTime;

    @GetMapping({"/","/index"})
    public String toIndex(){
        return "hello";
    }

    @GetMapping("/login")
    public Result login(String userName, String password, HttpServletResponse response) {

        User user = userService.getUserByName(userName);
        if(ObjectUtils.isEmpty(user)){
            return new Result(LOGIN_ERR,"用户名不存在");
        }else {
            if(user.getPassword().equals(password)){
                try {
                    Algorithm algorithm = Algorithm.HMAC256(this.secret_key);
                    String token = JWT.create()
                            .withIssuer("auth0")
                            .withClaim("userName", userName).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * expTime))
                            .sign(algorithm);
                    response.setHeader("token",token);
                    return new Result(LOGIN_OK,"登录成功",token);
                } catch (JWTCreationException exception){
                    // Invalid Signing configuration / Couldn't convert Claims.
                }

            }else {
                return new Result(LOGIN_ERR, "密码错误");
            }
        }return null;
    }

}
