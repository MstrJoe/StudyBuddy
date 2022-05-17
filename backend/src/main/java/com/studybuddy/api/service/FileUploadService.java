package com.studybuddy.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

@Service
public class FileUploadService {

    private String uploadFolder = "public/uploads";
    private Path uploadPath;

    public FileUploadService() {
        this.uploadPath = Paths.get(this.uploadFolder);
    }

    /**
     * From tutorial repo: https://spring.io/guides/gs/uploading-files
     */
    public String store(MultipartFile file) throws Exception {

        String uniqueID = UUID.randomUUID().toString();
        String fileName = uniqueID + "_" + file.getOriginalFilename();

        // https://stackoverflow.com/questions/4169713/how-to-check-a-uploaded-file-whether-it-is-an-image-or-other-file
        try {
            ImageIO.read(file.getInputStream()).toString();
            // It's an image (only BMP, GIF, JPG and PNG are recognized).
        } catch (Exception e) {
            throw new Exception("Only images are allowed");
        }

        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.uploadPath.resolve(fileName));
            return fileName;

        } catch (IOException e) {
            System.out.println(e);
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }

    }
}
