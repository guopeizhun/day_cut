package com.letg.day_cut.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void write(HttpServletResponse response, String contentType, Object obj){
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;

        try {
            s = objectMapper.writeValueAsString(obj);
            response.getWriter().write(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}