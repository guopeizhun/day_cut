package com.letg.day_cut.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 菜单表
 * @TableName t_menu
 */
public class MenuVO implements Serializable {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路由
     */
    private String route;

    /**
     * 菜单id
     */
    private Integer pid;

    /**
     * 图标名称
     */
    private String icon;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;


    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    private static final long serialVersionUID = 1L;





    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 菜单路由
     */
    public String getRoute() {
        return route;
    }

    /**
     * 菜单路由
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * 菜单id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 菜单id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 图标名称
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标名称
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuVO menuVO = (MenuVO) o;
        return Objects.equals(menuName, menuVO.menuName) && Objects.equals(route, menuVO.route) && Objects.equals(pid, menuVO.pid) && Objects.equals(icon, menuVO.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuName, route, pid, icon);
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "menuName='" + menuName + '\'' +
                ", route='" + route + '\'' +
                ", pid=" + pid +
                ", icon='" + icon + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}