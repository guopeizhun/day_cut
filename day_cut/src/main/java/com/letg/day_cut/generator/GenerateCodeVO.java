package com.letg.day_cut.generator;

import lombok.Data;

import java.util.List;

@Data
public class GenerateCodeVO {
    private List<String> tablesName;
    private String packgeName;
    private String tablePrefix;
    private boolean hasController;
    private boolean hasServiceImpl;
    private boolean hasService;
    private boolean hasMapper;
    private boolean hasModel;
    private boolean hasCustomMod;
    private String customMod;

}
