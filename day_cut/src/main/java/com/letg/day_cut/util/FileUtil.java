package com.letg.day_cut.util;

import java.io.File;

public class FileUtil {
    public static void mkdirs(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
