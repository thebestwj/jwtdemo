package com.learn.jwt.jwtdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
@Data
@AllArgsConstructor
public class UserAccount {
    private int uid;
    private String name;
    private String pw;
}
