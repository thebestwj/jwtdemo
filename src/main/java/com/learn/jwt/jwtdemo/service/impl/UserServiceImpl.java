package com.learn.jwt.jwtdemo.service.impl;

import com.learn.jwt.jwtdemo.entity.UserAccount;
import com.learn.jwt.jwtdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add(UserAccount userAccount) {

    }

    @Override
    public void delete(int uid) {

    }

    @Override
    public UserAccount getById(int uid) {
        return new UserAccount(1,"testAccount","testPW");
    }

    @Override
    public UserAccount getByName(String name) {
        if (name.equals("testAccount")) return new UserAccount(1,"testAccount","testPW");
        return null;
    }
}
