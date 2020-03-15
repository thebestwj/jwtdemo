package com.learn.jwt.jwtdemo.service;

import com.learn.jwt.jwtdemo.entity.UserAccount;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
public interface UserService {
    void add(UserAccount userAccount);
    void delete(int uid);
    UserAccount getById(int uid);
    UserAccount getByName(String name);
}
