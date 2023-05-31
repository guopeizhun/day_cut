package com.letg.day_cut.controller;


import com.letg.day_cut.annotion.IgnoreAuth;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.CommonLoginVO;
import com.letg.day_cut.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")

public class LoginController {


    @Autowired
    private LoginService loginService;
    /**
     * 普通账号密码登录
     * @return
     */
    @IgnoreAuth
    @PostMapping("/common")
    public Result common(@RequestBody CommonLoginVO loginVO){
        return loginService.common(loginVO);
    }



}
