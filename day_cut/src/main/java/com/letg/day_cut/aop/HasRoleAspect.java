package com.letg.day_cut.aop;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.letg.day_cut.annotion.HasRole;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.exception.AnonymousExcetion;
import com.letg.day_cut.model.Role;
import com.letg.day_cut.service.RoleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class HasRoleAspect {

    @Autowired
    private RoleService roleService;

    @Before("@annotation(hasRole)")
    public void before(JoinPoint joinPoint, HasRole hasRole){
        String[] list = hasRole.roleList();
        LambdaQueryWrapper<Role> wr = new LambdaQueryWrapper<>();
        wr.eq(Role::getUid, UserConstant.getUid());
        List<Role> roleList = roleService.list(wr);
        List<String> roleNameList = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
        for (String roleName : list) {
            if(roleNameList.contains(roleName)){
                return;
            }
        }

        //TODO
        throw new AnonymousExcetion();

    }
}
