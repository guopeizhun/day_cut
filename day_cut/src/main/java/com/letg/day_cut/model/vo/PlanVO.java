package com.letg.day_cut.model.vo;

import com.letg.day_cut.annotion.AutoFilled;
import com.letg.day_cut.constant.UserConstant;
import com.letg.day_cut.model.Task;

import java.util.Date;
import java.util.List;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
