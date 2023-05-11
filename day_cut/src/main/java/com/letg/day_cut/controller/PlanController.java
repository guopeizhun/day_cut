package com.letg.day_cut.controller;


import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.PlanVO;
import com.letg.day_cut.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
