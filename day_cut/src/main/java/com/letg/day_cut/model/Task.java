package com.letg.day_cut.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 任务
 * @TableName t_task
 */
@TableName(value ="t_task")
@Data
public class Task implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 任务开始时间
     */
    private Date timeStart;

    /**
     * 任务结束时间
     */
    private Date timeEnd;

    /**
     * 任务详情
     */
    private String detail;

    /**
     * 是否完成
     */
    private Integer isCompleted;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 任务id
     */
    private Integer pid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Task other = (Task) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTimeStart() == null ? other.getTimeStart() == null : this.getTimeStart().equals(other.getTimeStart()))
            && (this.getTimeEnd() == null ? other.getTimeEnd() == null : this.getTimeEnd().equals(other.getTimeEnd()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getIsCompleted() == null ? other.getIsCompleted() == null : this.getIsCompleted().equals(other.getIsCompleted()))
            && (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTimeStart() == null) ? 0 : getTimeStart().hashCode());
        result = prime * result + ((getTimeEnd() == null) ? 0 : getTimeEnd().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getIsCompleted() == null) ? 0 : getIsCompleted().hashCode());
        result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timeStart=").append(timeStart);
        sb.append(", timeEnd=").append(timeEnd);
        sb.append(", detail=").append(detail);
        sb.append(", isCompleted=").append(isCompleted);
        sb.append(", order=").append(order);
        sb.append(", pid=").append(pid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}