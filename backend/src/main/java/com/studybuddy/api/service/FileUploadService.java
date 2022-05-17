package com.studybuddy.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileUploadService {

    private String folder = "/uploads";

    public String store(String folder, MultipartFile file) {
        String path = folder + "";
        return path;
    }
}
