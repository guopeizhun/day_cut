package com.letg.day_cut.interceptor;

import com.letg.day_cut.annotion.IgnoreAuth;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.util.JwtUtil;
import com.letg.day_cut.util.ResponseUtil;
import org.apache.ibatis.javassist.util.proxy.MethodHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.letg.day_cut.util.JwtUtil.*;

public class AuthencationInterceptor extends HandlerInterceptorAdapter {


    private static final String acceptIp = "127.0.0.1";
    private final String unNeedAuthArr[] = {
            "/v1/comment",
            "/v1/comment/up/**",
            "/v1/login",
            "/v1/feedback/**"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //不是方法处理
        if (!(handler instanceof HandlerMethod)) {
           return true;
        }
        //如果不需要认证
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(IgnoreAuth.class)) {
            return true;
        }

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
                    ResponseUtil.write(response, "application/json", Result.fail().msg("IP不允许"));
                    return false;
                }
            }
        }

        //保存用户id
        UserConstant.uid.set((Integer) JwtUtil.getInfo(token, UID));
        UserConstant.username.set((String) JwtUtil.getInfo(token, USERNAME));
        UserConstant.nickname.set((String) JwtUtil.getInfo(token, NICKNAME));
        UserConstant.ip.set(request.getHeader("Origin"));
        JwtUtil.expire(token);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserConstant.uid.remove();
    }
}