package com.letg.day_cut.service.impl;

import com.letg.day_cut.mapper.GenerateMapper;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GenerateServiceImpl implements GenerateService {


    @Autowired
    private GenerateMapper mapper;

    @Override
    public Result showTables(String tableName) {
        List<Map> tables = mapper.showTables(tableName);
        return Result.ok().data(tables);
    }

    @Override
    public Result descTable(String tableName) {
        List<Map> maps = mapper.descTable(tableName);
        return Result.ok().data(maps);
    }
}
