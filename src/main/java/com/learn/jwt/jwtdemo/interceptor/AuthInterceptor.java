package com.learn.jwt.jwtdemo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.learn.jwt.jwtdemo.annonation.TokenNeeded;
import com.learn.jwt.jwtdemo.entity.UserAccount;
import com.learn.jwt.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by white_wolf on 2020/3/15.
 *
 * @author thebestwj
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.getMethodAnnotation(TokenNeeded.class) == null) return true;

        if (token == null) {
            response.setStatus(400);
            return false;
        }
        // 获取 token 中的 user id
        String uid;
        try {
            uid = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            response.setStatus(402);
            throw new RuntimeException("401");
        }
        UserAccount user = userService.getById(Integer.parseInt(uid));
        if (user == null) {
            response.setStatus(404);
            throw new RuntimeException("用户不存在，请重新登录");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPw())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401");
        }
        return true;
    }
}
