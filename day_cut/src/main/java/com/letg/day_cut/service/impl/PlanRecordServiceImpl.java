package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.Plan;
import com.letg.day_cut.model.PlanRecord;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.PlanRecordService;
import com.letg.day_cut.mapper.PlanRecordMapper;
import com.letg.day_cut.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 86158
 * @description 针对表【t_plan_record(计划执行记录)】的数据库操作Service实现
 * @createDate 2023-05-04 11:41:00
 */
@Service
public class PlanRecordServiceImpl extends ServiceImpl<PlanRecordMapper, PlanRecord>
        implements PlanRecordService {


    /**
     * 获取计划的当月完成情况
     *
     * @param planId
     * @return
     */
    @Override
    public Result getRecordByPlanId(Integer planId) {
        LambdaQueryWrapper<PlanRecord> wr = new LambdaQueryWrapper<>();
        wr.eq(PlanRecord::getPlanId, planId);
        Date lastDay = CommonUtil.getMonthLastDay();
        Date firstDay = CommonUtil.getMonthFirstDay();
        wr.ge(PlanRecord::getCreateTime, firstDay);
        wr.le(PlanRecord::getCreateTime, lastDay);
        wr.orderByAsc(PlanRecord::getCreateTime);
        List<PlanRecord> planRecordList = baseMapper.selectList(wr);
        List<Integer> compeletedStatusArr = getCompeletedStatusArr(planRecordList);
        return Result.ok().data(compeletedStatusArr);
    }

    /**
     * 日期=index+1    完成情况=value
     * 获取本月的完成情况 [0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
     * @param list
     * @return
     */
    List<Integer> getCompeletedStatusArr(List<PlanRecord> list) {
        int size = CommonUtil.getMonthLastDay().getDate();
        int nowDay = new Date().getDate();
        //获取有记录的日期
        Map<Integer, Integer> map = list.stream()
                .collect(Collectors.toMap(
                        item -> item.getCreateTime().getDate(),
                        item -> item.getIsCompleted(),
                        (oldValue, newValue) -> newValue));
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            if (!map.containsKey(i)) {
                //未被记录则记录为未完成
                arr.add(PlanRecord.RecordStatus.UN_COMP.getCode());
            } else {
                //被记录
                Integer status = map.get(i);
                //判断是否是当日
                if (i == nowDay) {
                    if (0 == status) {
                        arr.add(PlanRecord.RecordStatus.UN_COMP_CURRENT.getCode());
                    }else {
                        arr.add(PlanRecord.RecordStatus.COMP_CURRENT.getCode());
                    }
                } else {
                   arr.add(status);
                }
            }
        }
        return arr;
    }


}




