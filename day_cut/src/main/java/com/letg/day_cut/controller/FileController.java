package com.letg.day_cut.controller;


import com.letg.day_cut.model.Result;
import com.letg.day_cut.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileController {


    @Autowired
    private FileService fileService;

    @PostMapping("/temp")
    public Result uploadTempFile(MultipartFile file){
        return fileService.uploadTempFile(file);
    }
}
