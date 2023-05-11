package com.letg.day_cut.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 设置
 * @TableName t_plugin
 */
@TableName(value ="t_plugin")
@Data
public class Plugin implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 是否订阅
     */
    private Integer subscribe;

    /**
     * 界面风格编号
     */
    private Integer styleType;

    /**
     * 字体编号
     */
    private Integer fontType;

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
        Plugin other = (Plugin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSubscribe() == null ? other.getSubscribe() == null : this.getSubscribe().equals(other.getSubscribe()))
            && (this.getStyleType() == null ? other.getStyleType() == null : this.getStyleType().equals(other.getStyleType()))
            && (this.getFontType() == null ? other.getFontType() == null : this.getFontType().equals(other.getFontType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSubscribe() == null) ? 0 : getSubscribe().hashCode());
        result = prime * result + ((getStyleType() == null) ? 0 : getStyleType().hashCode());
        result = prime * result + ((getFontType() == null) ? 0 : getFontType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", subscribe=").append(subscribe);
        sb.append(", styleType=").append(styleType);
        sb.append(", fontType=").append(fontType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}