package com.letg.day_cut.service;

import com.letg.day_cut.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.MenuVO;

/**
* @author Administrator
* @description 针对表【t_menu(菜单表)】的数据库操作Service
* @createDate 2023-05-11 09:21:55
*/
public interface MenuService extends IService<Menu> {

    Result getMenuTree();

    Result addMenu(MenuVO menuVO);



    Result load(Integer menuId);

    Result getChild(Integer menuId);

    Result updateMenu(MenuVO menuVO);
}
