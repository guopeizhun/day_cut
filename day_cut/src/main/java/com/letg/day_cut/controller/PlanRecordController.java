package com.letg.day_cut.controller;


import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.PlanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 计划完成记录
 */
@RestController
@RequestMapping("/planRecord")
public class PlanRecordController {

    @Autowired
    private PlanRecordService planRecordService;

    @GetMapping("/{planId}")
    public Result getRecord(@PathVariable Integer planId){
        return planRecordService.getRecordByPlanId(planId);
    }
}
