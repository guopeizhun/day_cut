package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.Menu;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.MenuVO;
import com.letg.day_cut.service.MenuService;
import com.letg.day_cut.mapper.MenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【t_menu(菜单表)】的数据库操作Service实现
 * @createDate 2023-05-11 09:21:55
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    /**
     * 获取菜单树
     * @return
     */
    @Override
    public Result getMenuTree() {
        LambdaQueryWrapper<Menu> wr = new LambdaQueryWrapper<>();
        wr.eq(Menu::getIsDeleted, 0);
        List<Menu> menuList = baseMapper.selectList(wr);

        //找到根节点
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getPid() == 0) {
               List<Menu> childList = getChildList(menuList,menuList.get(i).getId());
               menuList.get(i).setChildList(childList);
            }
        }
        return Result.ok().data(menuList.stream().filter(item->item.getPid() == 0).collect(Collectors.toList()));
    }

    private List<Menu> getChildList(List<Menu> menuList, Integer id) {
        return menuList.stream().filter(item->item.getPid() == id).collect(Collectors.toList());
    }

    /**
     * 增加菜单
     * @param menuVO
     * @return
     */
    @Override
    public Result addMenu(MenuVO menuVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO,menu);
        baseMapper.insert(menu);
        return Result.ok();
    }



    /**
     * 加载菜单详情
     * @param menuId
     * @return
     */
    @Override
    public Result load(Integer menuId) {
        Menu menu = baseMapper.selectById(menuId);
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menu,menuVO);
        return Result.ok().data(menuVO);
    }

    /**
     * 获取子菜单
     * @param menuId
     * @return
     */
    @Override
    public Result getChild(Integer menuId) {
        LambdaQueryWrapper<Menu> wr = new LambdaQueryWrapper<>();
        wr.eq(Menu::getPid,menuId);
        List<Menu> menuList = baseMapper.selectList(wr);
        return Result.ok().data(menuList);
    }

    @Override
    public Result updateMenu(MenuVO menuVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO,menu);
        baseMapper.updateById(menu);
        return Result.ok();
    }

    /**
     * 构建菜单树
     *
     * @param menuList
     * @return
     */
    private void buildMenuTree(List<Menu> menuList, Menu fMenu) {
        for (Menu menu : menuList) {
            if(menu.getPid() == fMenu.getId()){
                fMenu.getChildList().add(menu);
                buildMenuTree(menuList,menu);
            }
        }
    }
}




