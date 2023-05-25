package com.letg.day_cut.exception;

import com.letg.day_cut.enums.ExcetionEnums;

/**
 * 未登录异常
 */
public class AnonymousExcetion extends BaseException{

    public AnonymousExcetion(){
        super(ExcetionEnums.Anonymous_Excetion);
    }



}
