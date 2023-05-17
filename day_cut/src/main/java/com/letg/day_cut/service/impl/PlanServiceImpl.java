package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.mapper.PlanRecordMapper;
import com.letg.day_cut.mapper.TaskMapper;
import com.letg.day_cut.model.Plan;
import com.letg.day_cut.model.PlanRecord;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.model.Task;
import com.letg.day_cut.model.vo.PlanVO;
import com.letg.day_cut.service.PlanService;
import com.letg.day_cut.mapper.PlanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PlanRecordMapper planRecordMapper;


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

    /**
     * 获取当前计划
     * @return
     */
    @Override
    public Result getCurrentPlan() {
        Integer uid = UserConstant.getUid();
        Plan plan = baseMapper.selectCurrentPlan(uid);
        if(null == plan){
            return Result.ok();
        }
        Integer planId = plan.getId();
        LambdaQueryWrapper<Task> wr = new LambdaQueryWrapper<>();
        wr.eq(Task::getPid,planId);
        List<Task> tasks = taskMapper.selectList(wr);
        PlanVO planVO = new PlanVO();
        BeanUtils.copyProperties(plan,planVO);
        planVO.setTaskList(tasks);
        return Result.ok().data(planVO);
    }


    /**
     * 获取计划详情
     * @param planId
     * @return
     */
    @Override
    public Result findPlan(Integer planId) {
        LambdaQueryWrapper<Plan> pwr = new LambdaQueryWrapper<>();
        pwr.eq(Plan::getId,planId);
        //当前用户
        pwr.eq(Plan::getUid,UserConstant.getUid());
        Plan plan = baseMapper.selectOne(pwr);

        if(null == plan){
            return Result.ok();
        }
        LambdaQueryWrapper<Task> twr = new LambdaQueryWrapper<>();
        twr.eq(Task::getPid,planId);
        List<Task> tasks = taskMapper.selectList(twr);
        PlanVO planVO = new PlanVO();
        BeanUtils.copyProperties(plan,planVO);
        planVO.setTaskList(tasks);
        return Result.ok().data(planVO);
    }

    /**
     * 查看计划列表
     * @return
     */
    @Override
    public Result findPlanList() {
        Integer uid = UserConstant.getUid();
        LambdaQueryWrapper<Plan> wr = new LambdaQueryWrapper<>();
        wr.eq(Plan::getUid,uid);
        List<Plan> planList = baseMapper.selectList(wr);
        return Result.ok().data(planList);
    }

    /**
     * 移除计划
     * @param planId
     * @return
     */
    @Override
    @Transactional
    public Result removePlan(Integer planId) {
        baseMapper.deleteById(planId);
        LambdaQueryWrapper<Task> wr1 = new LambdaQueryWrapper<>();
        wr1.eq(Task::getPid,planId);
        taskMapper.delete(wr1);
        LambdaQueryWrapper<PlanRecord> wr2 = new LambdaQueryWrapper<>();
        wr2.eq(PlanRecord::getPlanId,planId);
        planRecordMapper.delete(wr2);
        return Result.ok();
    }


}




