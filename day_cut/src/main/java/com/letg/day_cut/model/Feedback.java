package com.letg.day_cut.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName t_feedback
 * 创建时间：2023:06:03 04:55:56
 */
@TableName(value ="t_feedback")
@Data
public class Feedback implements Serializable {
    /***
     * 用户id
     **/
    private Integer uid;
    /***
     * 是否已读
     **/
    private Integer isReaded;
    /***
     * id
     **/
    private Integer id;
    /***
     * 详细内容
     **/
    private String detail;
    /***
     * 创建时间
     **/
    private Date createTime;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
