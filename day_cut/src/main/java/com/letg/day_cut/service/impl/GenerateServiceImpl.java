package com.letg.day_cut.service.impl;

import com.letg.day_cut.generator.CodeGenerator;
import com.letg.day_cut.generator.GenerateCodeVO;
import com.letg.day_cut.mapper.GenerateMapper;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * params:[columnList,modelName,packgeName....]
     *
     * @param vo
     * @return
     */
    @Override
    public Result generateCode(GenerateCodeVO vo) {
        for (String tableName : vo.getTablesName()) {
            List<Object> params = new ArrayList<>();
            List<Map> data = (List<Map>) descTable(tableName).getData();
            ArrayList<Map> list = new ArrayList<>();
            for (int i = data.size() - 1; i >= 0; i--) {
                Map map = data.get(i);

                HashMap<String, Object> m = new HashMap<>();
                m.put("COLUMN_NAME", map.get("COLUMN_NAME").toString());
                m.put("COLUMN_TYPE", map.get("DATA_TYPE").toString());
                m.put("COLUMN_COMMENT", map.get("COLUMN_COMMENT").toString());
                m.put("COLUMN_KEY", StringUtils.isEmpty(map.get("COLUMN_KEY")));
                list.add(m);
            }
            params.add(list);
            params.add(getModelName(tableName, vo.getTablePrefix()));
            params.add(vo.getPackgeName());
            params.add(vo.isHasController());
            params.add(vo.isHasService());
            params.add(vo.isHasServiceImpl());
            params.add(vo.isHasModel());
            params.add(vo.isHasMapper());
            params.add(tableName);
            CodeGenerator.generate(params);
        }
        return Result.ok();
    }

    /**
     * 获取实体名称
     *
     * @param tableName
     * @param prefix
     * @return
     */
    private static String getModelName(String tableName, String prefix) {
        String modelName = tableName.replace(prefix, "");
        modelName = CodeGenerator.columnToProperty(modelName);
        return modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
    }




}
