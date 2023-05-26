package com.letg.day_cut.model.vo;


import lombok.Data;

@Data
public class CommonLoginVO {
    private String username;
    private String password;
    private String verifyCode;
    private String uuid;
}
