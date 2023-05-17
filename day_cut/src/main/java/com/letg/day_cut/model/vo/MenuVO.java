package com.letg.day_cut.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 菜单表
 * @TableName t_menu
 */

@Data
public class MenuVO implements Serializable {

    private Integer id;

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




    private static final long serialVersionUID = 1L;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuVO menuVO = (MenuVO) o;
        return Objects.equals(id, menuVO.id) && Objects.equals(menuName, menuVO.menuName) && Objects.equals(route, menuVO.route) && Objects.equals(pid, menuVO.pid) && Objects.equals(icon, menuVO.icon) && Objects.equals(isDeleted, menuVO.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuName, route, pid, icon, isDeleted);
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", route='" + route + '\'' +
                ", pid=" + pid +
                ", icon='" + icon + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}