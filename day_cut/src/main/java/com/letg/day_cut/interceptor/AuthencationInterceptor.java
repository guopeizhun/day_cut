package com.letg.day_cut.interceptor;

import com.letg.day_cut.model.Result;
import com.letg.day_cut.util.JwtUtil;
import com.letg.day_cut.util.ResponseUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthencationInterceptor extends HandlerInterceptorAdapter {

    public static ThreadLocal<String> authThreadLocal = new ThreadLocal<>();
    private static final String acceptIp = "http://localhost:8081";
    private final String unNeedAuthArr[] = {
            "/v1/comment",
            "/v1/comment/up/**",
            "/v1/login",
            "/v1/feedback/**"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String method = request.getMethod();
        String token = request.getHeader("token");
        String requestURL = request.getRequestURI();
        //拦截一切关于对于数据有操作的
        if (method.equals("POST") || method.equals("DELETE") || method.equals("PUT")) {
            /**
             * 判断token是否存在且合法
             */


            if (token == null || !JwtUtil.checkToken(token)) {
                //如果请求ip不是认可的IP则返回失败
                String origin = request.getHeader("Origin");
                if (!origin.equals(acceptIp)) {
                    ResponseUtil.write(response, "application/json", Result.fail().msg("权限不足"));
                    return false;
                }
            }
        }

//        redisUtil.setVal(RedisConstant.TEMP_TOKEN_KEY, token);
        authThreadLocal.set(token);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        redisUtil.delKey(RedisConstant.TEMP_TOKEN_KEY);
        authThreadLocal.remove();
    }
}