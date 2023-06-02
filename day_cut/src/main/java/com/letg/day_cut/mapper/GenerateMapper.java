package com.letg.day_cut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.letg.day_cut.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface GenerateMapper extends BaseMapper<Menu> {
    List<Map> showTables(@Param("tName") String tName);

    List<Map> descTable(@Param("tName") String tName);
}




