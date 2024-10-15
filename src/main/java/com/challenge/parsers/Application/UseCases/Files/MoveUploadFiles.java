package com.challenge.parsers.Application.UseCases.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class MoveUploadFiles {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${input.files.path}")
    private String inputFilesPath;

    public void execute() throws IOException {
        Path inputPath = Paths.get(uploadDir);
        Files.createDirectories(Paths.get(inputFilesPath));
        try (Stream<Path> paths = Files.walk(inputPath)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                try {
                    Path targetPath = Paths.get(inputFilesPath, file.getFileName().toString());
                    Files.move(file, targetPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to move file: " + file.getFileName(), e);
                }
            });
        }
    }
}