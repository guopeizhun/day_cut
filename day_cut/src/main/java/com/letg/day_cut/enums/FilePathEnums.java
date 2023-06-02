package com.letg.day_cut.enums;

import com.letg.day_cut.constant.Constants;
import com.letg.day_cut.constant.SystemConstants;
import lombok.Data;


public enum FilePathEnums {
    GENERATOR_TEMP_FILE("D:\\generator\\temp\\");

    private String path;


    FilePathEnums(String path) {
        this.path = path;
    }

    public static String getNetPath(FilePathEnums pathEnum) {
        String p = pathEnum.getPath();
        p = p.substring(p.indexOf(":") + 1);
        p = p.replaceAll("\\\\", "/");
        return Constants.HTTP + SystemConstants.localIp + p;
    }

    public String getPath() {
        return path;
    }

    public static String getFileNetPath(String localFilePath) {
        for (FilePathEnums pathEnum : FilePathEnums.values()) {
            if (localFilePath.contains(pathEnum.getPath())) {
                String netPath = getNetPath(pathEnum);
                String res = localFilePath.replace(pathEnum.getPath(), netPath);
                return res;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(getFileNetPath(GENERATOR_TEMP_FILE.getPath()));
    }
}
