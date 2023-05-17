package com.letg.day_cut.controller;


import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.PlanVO;
import com.letg.day_cut.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {


    @Autowired
    private PlanService planService;

    /**
     * 制定计划
     * @param planVO
     * @return
     */
    @PostMapping("/formulate")
    public Result formulate(PlanVO planVO){
        return planService.formulate(planVO);
    }

    /**
     * 获取当前计划
     * @return
     */
    @GetMapping("/getCurrentPlan")
    public Result getCurrentPlan(){
        return planService.getCurrentPlan();
    }

    /**
     * 查找计划详情
     * @param planId
     * @return
     */
    @GetMapping("/findPlan/{planId}")
    public Result findPlan(@PathVariable Integer planId){
        return planService.findPlan(planId);
    }


    /**
     * 获取计划列表
     * @return
     */
    @GetMapping("/findPlanList")
    public Result findPlanList(){
        return planService.findPlanList();
    }

    /**
     * 删除计划
     */
    @DeleteMapping("/remove/{planId}")
    public Result removePlan(@PathVariable Integer planId){
        return planService.removePlan(planId);
    }
}
