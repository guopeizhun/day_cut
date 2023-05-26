package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.letg.day_cut.constant.CacheConstants;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.User;
import com.letg.day_cut.model.vo.CommonLoginVO;
import com.letg.day_cut.service.LoginService;
import com.letg.day_cut.service.UserService;
import com.letg.day_cut.util.JwtUtil;
import com.letg.day_cut.util.RedisCache;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    @Override
    public Result common(CommonLoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        String verifyCode = loginVO.getVerifyCode();

        String actualCode = redisCache.getCacheObject(CacheConstants.CAPTCHA_CODE_KEY+loginVO.getUuid());
        //图片验证码判断
        if(StringUtils.isEmpty(actualCode)){
            return Result.fail().msg("验证码失效，请重新获取");
        }
        if (!actualCode.equals(verifyCode)) {
            return Result.fail().msg("验证码错误");
        }
        LambdaQueryWrapper<User> wr = new LambdaQueryWrapper<>();
        wr.eq(User::getUserName, username);
        User user = userService.getOne(wr);
        if (null == user) {
            return Result.fail().msg("不存在该用户");
        }
        if (!user.getPassword().equals(password)) {
            return Result.fail().msg("密码错误");
        }

        String token = JwtUtil.createJwtToken(user);


        return Result.ok().data(token);
    }
}
