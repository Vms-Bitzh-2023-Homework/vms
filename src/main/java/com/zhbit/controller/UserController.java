package com.zhbit.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhbit.common.Perms;
import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.pojo.User;
import com.zhbit.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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

    @GetMapping("/login")
    public Result login(String userName, String password, HttpServletResponse response) {

        User user = userService.getUserByName(userName);
        if (ObjectUtils.isEmpty(user)) {
            return new Result(LOGIN_ERR, "用户名不存在");
        } else {
            if (user.getPassword().equals(password)) {
                try {
                    Algorithm algorithm = Algorithm.HMAC256(this.secret_key);
                    String token = JWT.create()
                            .withIssuer("auth0")
                            .withClaim("id", user.getId())
                            .withClaim("perms", user.getPerms())
                            .withClaim("userName", userName).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * expTime))
                            .sign(algorithm);
                    response.setHeader("token", token);
                    return new Result(LOGIN_OK, "登录成功", token);
                } catch (JWTCreationException exception) {
                    // Invalid Signing configuration / Couldn't convert Claims.
                }

            } else {
                return new Result(LOGIN_ERR, "密码错误");
            }
        }
        return null;
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

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret_key);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("auth0")
                    // reusable verifier instance
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String perms = String.valueOf(decodedJWT.getClaim("perms"));
            if (perms.equals(Perms.admin)) {
                User user = new User();
                user.setUserName(map.get("userName"));
                user.setPassword(map.get("password"));
                user.setAdPhone(map.get("adPhone"));
                boolean flag = userService.save(user);
                String msg = "save success";
                return new Result(flag ? StatusCode.SAVE_OK : StatusCode.SAVE_ERR, msg);
            }else {
                return new Result(StatusCode.SAVE_ERR, "您没有权限添加用户");
            }
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}