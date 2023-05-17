package com.letg.day_cut.mapper;

import com.letg.day_cut.model.Plugin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【t_plugin(设置)】的数据库操作Mapper
* @createDate 2023-05-15 10:45:35
* @Entity com.letg.day_cut.model.Plugin
*/

@Mapper
public interface PluginMapper extends BaseMapper<Plugin> {

}




