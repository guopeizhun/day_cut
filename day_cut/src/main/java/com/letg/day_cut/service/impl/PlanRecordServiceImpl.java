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
        Date lastOfMonth = CommonUtil.getMonthLastDay();
        Date firstOfMonth = CommonUtil.getMonthFirstDay();
        Date firstOfYear = CommonUtil.getYearFirstDay();
        Date lastOfYear = CommonUtil.getYearLastDay();

        //完成情况
        LambdaQueryWrapper<PlanRecord> wr = new LambdaQueryWrapper<>();
        wr.eq(PlanRecord::getPlanId, planId);
        wr.ge(PlanRecord::getCreateTime, firstOfMonth);
        wr.le(PlanRecord::getCreateTime, lastOfMonth);
        wr.orderByAsc(PlanRecord::getCreateTime);
        List<PlanRecord> planRecordList = baseMapper.selectList(wr);
        List<Integer> compeletedStatusArr = getCompeletedStatusArr(planRecordList);
        //查询一个月的总完成数
        LambdaQueryWrapper<PlanRecord> pwr1 = new LambdaQueryWrapper<>();
        pwr1.eq(PlanRecord::getIsCompleted,1);
        pwr1.ge(PlanRecord::getCreateTime,firstOfMonth);
        pwr1.le(PlanRecord::getCreateTime,lastOfMonth);
        Long monthCount = baseMapper.selectCount(pwr1);
        //查询一年的总完成数
        LambdaQueryWrapper<PlanRecord> pwr2 = new LambdaQueryWrapper<>();
        pwr2.eq(PlanRecord::getIsCompleted,1);
        pwr2.ge(PlanRecord::getCreateTime, firstOfYear);
        pwr2.le(PlanRecord::getCreateTime,lastOfYear);
        Long yearCount = baseMapper.selectCount(pwr2);

        Map<String, Object> map = new HashMap<>();
        map.put("monthCount",monthCount);
        map.put("yearCount",yearCount);
        map.put("compeletedStatusArr",compeletedStatusArr);
        return Result.ok().data(map);
    }

    /**
     * 日期=index+1    完成情况=value
     * 获取本月的完成情况 [0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
     * @param list
     * @return
     */
    private List<Integer> getCompeletedStatusArr(List<PlanRecord> list) {
        int size = CommonUtil.getMonthLastDay().getDate();
        int nowDay = new Date().getDate();
        /**
         * map
         * key->日期
         * val->完成情况
         */
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




