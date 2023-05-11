package com.letg.day_cut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letg.day_cut.model.Task;
import com.letg.day_cut.service.TaskService;
import com.letg.day_cut.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author 86158
* @description 针对表【t_task(任务)】的数据库操作Service实现
* @createDate 2023-05-04 11:41:00
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




