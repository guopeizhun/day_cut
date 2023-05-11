package com.letg.day_cut.mapper;

import com.letg.day_cut.model.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86158
* @description 针对表【t_task(任务)】的数据库操作Mapper
* @createDate 2023-05-04 11:41:00
* @Entity com.letg.day_cut.model.Task
*/

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    void batchInsert(List<Task> taskList);
}




