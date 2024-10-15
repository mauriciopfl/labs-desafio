package com.challenge.parsers.Application.UseCases.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFiles {

    @Value("${upload.dir}")
    private String uploadDir;

    public void execute(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty() || !file.getContentType().equals("text/plain")) {
                throw new IllegalArgumentException("One of the files is empty or not a text file.");
            }
            Path filePath = Paths.get(uploadDir + "/" + file.getOriginalFilename());
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
        }
    }
}