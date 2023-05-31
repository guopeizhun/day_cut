package com.letg.day_cut.model;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class PageInfo {
    private List<Object> data;
    private Long total;
}
