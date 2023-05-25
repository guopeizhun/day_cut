package com.letg.day_cut.aop;


import com.letg.day_cut.constant.ContenTypeConstants;
import com.letg.day_cut.exception.BaseException;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Component
public class ExceptionAdvice {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    /**
     * 业务处理异常
     * @param e
     */
    @ExceptionHandler(BaseException.class)
    public void baseHandler(BaseException e){
        String msg = e.getMsg();
        int code = e.getCode();
        Result result = Result.fail().code(code).msg(msg);
        ResponseUtil.write(response, ContenTypeConstants.JSON,result);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public void otherHandler(Exception e){
        Result result = Result.fail().msg("系统出现异常，请联系管理员");
        ResponseUtil.write(response, ContenTypeConstants.JSON,result);
    }
}
