package com.zhbit.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhbit.common.Constant;
import com.zhbit.common.Result;
import com.zhbit.common.StatusCode;
import com.zhbit.mapper.UserMapper;
import com.zhbit.pojo.User;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.zhbit.common.StatusCode.LOGIN_ERR;
import static com.zhbit.common.StatusCode.LOGIN_OK;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Value("${jwt.secret_key}")
    private String secret_key;

    @Value("${jwt.exp_time}")
    private int expTime;


    @Override
    public User getUserByName(String UserName) {
        return userMapper.getUserByName(UserName);
    }

    @Override
    public Map Login(String userName, String password) {
        User user = getUserByName(userName);
        if(ObjectUtils.isEmpty(user)){
            HashMap map1 = new HashMap();
            map1.put("statusCode", LOGIN_ERR);
            map1.put("msg","用户名不正确");
            return map1;
        }else {
            if(user.getPassword().equals(password)){
                try {
                    Algorithm algorithm = Algorithm.HMAC256(this.secret_key);
                    String token = JWT.create()
                            .withIssuer("auth0")
                            .withClaim("id",user.getId())
                            .withClaim("perms",user.getPerms())
                            .withClaim("userName", userName).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * expTime))
                            .sign(algorithm);
                    HashMap map2 = new HashMap();
                    map2.put("statusCode", LOGIN_OK);
                    map2.put("msg","登陆成功");
                    map2.put("token",token);
                    return map2;
                } catch (JWTCreationException exception){
                    // Invalid Signing configuration / Couldn't convert Claims.
                }

            }else {
                HashMap map2 = new HashMap();
                map2.put("statusCode", LOGIN_ERR);
                map2.put("msg","密码错误");
                return map2;
            }
        }return null;
    }

    @Override
    public Result addUser(Map<String, String> map,String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret_key);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("auth0")
                    // reusable verifier instance
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String  perms = String.valueOf(decodedJWT.getClaim("perms"));
            if (perms.equals(Constant.admin)) {
                User user = new User();
                user.setUserName(map.get("userName"));
                user.setPassword(map.get("password"));
                user.setAdPhone(map.get("adPhone"));
                user.setPerms(map.get("perms"));
                boolean flag = save(user);
                String msg = "save success";
                return new Result(flag ? StatusCode.SAVE_OK : StatusCode.SAVE_ERR, msg);
            }else {
                return new Result(StatusCode.SAVE_ERR, "您没有权限添加用户",null);
            }
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

}
