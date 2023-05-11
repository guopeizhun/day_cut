package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.User;
import com.letg.day_cut.service.UserService;
import com.letg.day_cut.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 86158
* @description 针对表【t_user(用户)】的数据库操作Service实现
* @createDate 2023-05-04 11:41:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




