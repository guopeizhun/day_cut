package com.letg.day_cut.controller;


import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.MenuVO;
import com.letg.day_cut.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping("/tree")
    public Result getMenuTree() {
        return menuService.getMenuTree();
    }

    /**
     * 增加菜单那
     *
     * @param menuVO
     * @return
     */
    @PostMapping
    public Result addMenu(@RequestBody MenuVO menuVO) {
        return menuService.addMenu(menuVO);
    }

    /**
     * 加载菜单的详情
     *
     * @param menuId
     * @return
     */
    @GetMapping("/load/{menuId}")
    public Result load(@PathVariable Integer menuId) {
        return menuService.load(menuId);
    }

    /**
     * 获得子菜单
     */
    @GetMapping("/child/{menuId}")
    public Result getChild(@PathVariable Integer menuId){
        return menuService.getChild(menuId);
    }

    /**
     * 隐藏或显示菜单
     */
    @PutMapping
    public Result updateMenu(@RequestBody MenuVO menuVO) {
        return menuService.updateMenu(menuVO);
    }

}
