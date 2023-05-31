package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.User;
import com.letg.day_cut.model.vo.UserVO;
import com.letg.day_cut.service.UserService;
import com.letg.day_cut.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 86158
* @description 针对表【t_user(用户)】的数据库操作Service实现
* @createDate 2023-05-04 11:41:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Result getInfo() {
        Integer uid = UserConstant.getUid();
        LambdaQueryWrapper<User> wr = new LambdaQueryWrapper<>();
        wr.eq(User::getId,uid);
        User user = baseMapper.selectOne(wr);
        Date createTime = user.getCreateTime();

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        Long dayCount = getComingDay(createTime);
        userVO.setDayCount(dayCount);

            return Result.ok().data(userVO);
    }

    private  Long getComingDay(Date createTime){
        Date now = new Date();
        long l = now.getTime() - createTime.getTime();
        Long oneDay = 1000*60*60*24L;
        return l / oneDay;
    }
}




