package com.learn.jwt.jwtdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
@AllArgsConstructor
@Getter
@Setter
public class CommonReturn<T> {
    String msg;
    T data;
    static public <T> CommonReturn<T> failed(){
        return new CommonReturn<T>("failed",null);
    }
    static public <T> CommonReturn<T> failed(String msg){
        return new CommonReturn<T>(msg,null);
    }
    static public <T> CommonReturn<T> success(){
        return new CommonReturn<T>("success",null);
    }
    static public <T> CommonReturn<T> successWithData(T data){
        return new CommonReturn<T>("success",data);
    }
}
