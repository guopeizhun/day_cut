package com.letg.day_cut.model.vo;

import com.letg.day_cut.annotion.DataMasking;
import lombok.Data;

@Data
public class UserVO {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 电话号码
     */
    @DataMasking(type = DataMasking.MaskingType.TEL)
    private String tel;

    /**
     * 电子邮箱
     */

    @DataMasking(type = DataMasking.MaskingType.EMAIL)
    private String email;

    /**
     * 来到天数
     */
    private Long dayCount;


    /**
     * 头像
     */

    private String avatar;

}


