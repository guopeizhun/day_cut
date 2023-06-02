package com.letg.day_cut.service.impl;

import com.letg.day_cut.enums.FilePathEnums;
import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.FileService;
import com.letg.day_cut.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void upload(MultipartFile file, String path) {
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 上传临时文件
     * @param file
     * @return
     */
    @Override
    public Result uploadTempFile(MultipartFile file) {
        String path = getFileAbuslutePath(file, FilePathEnums.GENERATOR_TEMP_FILE.getPath());
        upload(file, path);
        return Result.ok().data(coverToNetPath(path));
    }


    /**
     * 获取文件的本地绝对路径
     * @param file
     * @param path
     * @return
     */
    private static String getFileAbuslutePath(MultipartFile file, String path) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        return path + fileName;
    }

    /**
     * 转换本地路径为网络路径
     *
     * @param localFilePath 本地路径文件
     * @return
     */
    private String coverToNetPath(String localFilePath) {
        return FilePathEnums.getFileNetPath(localFilePath);

    }

}
