package com.letg.day_cut.mapper;

import com.letg.day_cut.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86158
* @description 针对表【t_user(用户)】的数据库操作Mapper
* @createDate 2023-05-04 11:41:00
* @Entity com.letg.day_cut.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




