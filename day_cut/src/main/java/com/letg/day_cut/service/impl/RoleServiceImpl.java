package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.Role;
import com.letg.day_cut.service.RoleService;
import com.letg.day_cut.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【t_role(角色表)】的数据库操作Service实现
* @createDate 2023-05-24 11:29:06
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




