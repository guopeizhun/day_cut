package com.letg.day_cut.mapper;

import com.letg.day_cut.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【t_role(角色表)】的数据库操作Mapper
* @createDate 2023-05-24 11:29:06
* @Entity com.letg.day_cut.model.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




