package com.letg.day_cut.service;

import com.letg.day_cut.model.Plan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.vo.PlanVO;

/**
* @author 86158
* @description 针对表【t_plan(日常计划)】的数据库操作Service
* @createDate 2023-05-04 11:41:00
*/
public interface PlanService extends IService<Plan> {

    Result formulate(PlanVO planVO);
}
