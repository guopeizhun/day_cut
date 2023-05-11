package com.letg.day_cut.mapper;

import com.letg.day_cut.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【t_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-05-11 09:21:55
* @Entity com.letg.day_cut.model.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}




