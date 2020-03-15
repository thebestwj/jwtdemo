package com.learn.jwt.jwtdemo.controller;

import com.learn.jwt.jwtdemo.annonation.TokenNeeded;
import com.learn.jwt.jwtdemo.entity.CommonReturn;
import com.learn.jwt.jwtdemo.entity.UserAccount;
import com.learn.jwt.jwtdemo.service.UserService;
import com.learn.jwt.jwtdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.learn.jwt.jwtdemo.entity.CommonReturn.failed;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/hi")
    private String hi(){
        return "hi" ;
    }

    @RequestMapping("/check")
    private String check(){
        return "checked" ;
    }

    @TokenNeeded
    @RequestMapping("/checkToken")
    private String checkToken(){
        return "TokenChecked" ;
    }

    @RequestMapping("/login")
    private  CommonReturn<String> login(String name,String pw){
        if (StringUtils.isEmpty(name)) return CommonReturn.failed("name is empty");
        if (StringUtils.isEmpty(pw)) return CommonReturn.failed("pw is empty");
        UserAccount userAccount = userService.getByName(name);
        if (Objects.isNull(userAccount)) return  CommonReturn.failed("no such user");
        if (pw.equals(userAccount.getPw())){
            String token= JwtUtil.getToken(userAccount);
            return CommonReturn.successWithData(token);
        }
        return CommonReturn.failed("wrong pw");
    }
}
