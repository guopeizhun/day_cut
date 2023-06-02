package com.letg.day_cut.service;


import com.letg.day_cut.model.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    void upload(MultipartFile file, String path);
    Result uploadTempFile(MultipartFile file);
}
