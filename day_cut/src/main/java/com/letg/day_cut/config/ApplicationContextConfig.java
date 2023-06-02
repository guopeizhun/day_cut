package com.letg.day_cut.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letg.day_cut.constant.SystemConstants;
import lombok.SneakyThrows;
import org.apache.catalina.core.StandardContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

@Configuration
public class ApplicationContextConfig implements ApplicationContextAware {

    @Value("${local.ip}")
    private String localIp;


    private  void loadMailMod() throws Exception {
        File file = ResourceUtils.getFile("classpath:mod/Mail.json");
        ObjectMapper mapper = new ObjectMapper();
        HashMap hashMap = mapper.readValue(file, HashMap.class);
        SystemConstants.mailModMap = hashMap;



    }

    private  void loadSystemConstans(){
        SystemConstants.localIp = localIp;
    }

    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        loadMailMod();
        loadSystemConstans();
    }

}
