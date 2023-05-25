package com.letg.day_cut.exception;

import com.letg.day_cut.enums.ExcetionEnums;

public class NoAccessException extends BaseException {

    public NoAccessException() {
        super(ExcetionEnums.NO_ACCESS_Exception);
    }
}
