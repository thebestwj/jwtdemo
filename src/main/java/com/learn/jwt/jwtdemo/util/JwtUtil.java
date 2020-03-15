package com.learn.jwt.jwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.learn.jwt.jwtdemo.entity.UserAccount;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
public class JwtUtil {
    public static String getToken(UserAccount userAccount){
        String token = JWT.create()
                .withAudience(String.valueOf(userAccount.getUid()))
                .sign(Algorithm.HMAC256(userAccount.getPw()));
        return token;
    }
}
