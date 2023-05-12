package com.letg.day_cut.service;

import com.letg.day_cut.model.PlanRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.letg.day_cut.model.Result;

/**
* @author 86158
* @description 针对表【t_plan_record(计划执行记录)】的数据库操作Service
* @createDate 2023-05-04 11:41:00
*/
public interface PlanRecordService extends IService<PlanRecord> {

    Result getRecordByPlanId(Integer planId);
}
