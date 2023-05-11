package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.mapper.TaskMapper;
import com.letg.day_cut.model.Plan;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.Task;
import com.letg.day_cut.model.vo.PlanVO;
import com.letg.day_cut.service.PlanService;
import com.letg.day_cut.mapper.PlanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86158
* @description 针对表【t_plan(日常计划)】的数据库操作Service实现
* @createDate 2023-05-04 11:41:00
*/
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan>
    implements PlanService{


    @Autowired
    private TaskMapper taskMapper;


    /**
     * 制定计划
     * @param planVO
     * @return
     */
    @Override
    public Result formulate(PlanVO planVO) {
        List<Task> taskList = planVO.getTaskList();
        taskMapper.batchInsert(taskList);
        Plan plan = new Plan();
        BeanUtils.copyProperties(planVO,plan);
        baseMapper.insert(plan);
        return Result.ok();
    }
}




