package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.Menu;
import com.letg.day_cut.service.MenuService;
import com.letg.day_cut.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【t_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-05-11 09:21:55
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




