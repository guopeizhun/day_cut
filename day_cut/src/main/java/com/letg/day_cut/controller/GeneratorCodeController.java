package com.letg.day_cut.controller;


import com.letg.day_cut.annotion.IgnoreAuth;
import com.letg.day_cut.generator.GenerateCodeVO;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
public class GeneratorCodeController {


    @Autowired
    private GenerateService generateService;


    @GetMapping("/showTables")
    @IgnoreAuth
    Result showTables(String tableName) {
        return generateService.showTables(tableName);
    }

    @GetMapping("/descTable")
    @IgnoreAuth
    Result descTable(String tableName) {
        return generateService.descTable(tableName);
    }


    @PostMapping("/generateCode")
    @IgnoreAuth
    public Result GenerateCode(@RequestBody GenerateCodeVO vo){
        return generateService.generateCode(vo);
    }
}
