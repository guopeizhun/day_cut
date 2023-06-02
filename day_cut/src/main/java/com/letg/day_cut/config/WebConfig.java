package com.letg.day_cut.config;


import com.letg.day_cut.constant.SystemConstants;
import com.letg.day_cut.enums.FilePathEnums;
import com.letg.day_cut.interceptor.AuthencationInterceptor;
import com.letg.day_cut.util.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.Optional;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthencationInterceptor()).excludePathPatterns("/**");


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        for (FilePathEnums pathEnums : FilePathEnums.values()) {
            String path = pathEnums.getPath();

            //如果没有就创建本地文件
            FileUtil.mkdirs(path);

            String netPath = FilePathEnums.getFileNetPath(path);
            //http://localhost:9200/xx/xx   ->/xx/xx
            String uri = netPath.substring(netPath.indexOf(SystemConstants.localIp) + SystemConstants.localIp.length());
            registry.addResourceHandler(uri+"/**") // /xx/xx/**
                    .addResourceLocations("file:"+path);//file:D:/xx/xx
        }
    }



    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//允许跨域的域名，可以用*表示允许任何域名使用
        corsConfiguration.addAllowedHeader("*");//允许任何请求头
        corsConfiguration.addAllowedMethod("*");//允许任何方法（post、get等）
        corsConfiguration.addExposedHeader("Authorization");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
//          .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }


}
