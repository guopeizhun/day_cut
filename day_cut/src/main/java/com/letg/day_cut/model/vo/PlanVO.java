package com.letg.day_cut.model.vo;

import com.letg.day_cut.annotion.AutoFilled;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.model.Task;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PlanVO {
    private Integer id;

    /**
     * 计划名称
     */
    private String name;

    /**
     * 计划描述
     */
    private String desc;

    /**
     * 创建者id
     */
    @AutoFilled(clazz = UserConstant.class,filedName = "uid")
    private Integer uid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 所有任务
     */
    private List<Task> taskList;


}
