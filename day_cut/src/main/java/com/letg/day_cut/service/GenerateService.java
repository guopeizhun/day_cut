package com.letg.day_cut.service;

import com.letg.day_cut.model.Result;

public interface GenerateService {
    Result showTables(String tableName);
    Result descTable(String tableName);
}
