package com.challenge.parsers.Application.UseCases.Files;

import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MoveFileToPathWithDateSubFolder {
    private static final Logger logger = LoggerFactory.getLogger(MoveFileToPathWithDateSubFolder.class);

    public void execute(String fileName, String toPath){
        try {
            String dateSubfolder = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String finalPath = toPath + "/" + dateSubfolder + "/" + new File(fileName).getName();
            try {
                Files.createDirectories(Paths.get(toPath));

                Files.createDirectories(Paths.get(toPath + "/" + dateSubfolder));
            } catch (Exception e) {
                logger.error("Error creating folder: {}", toPath + "/" + dateSubfolder, e);
            }
            Files.move(Paths.get(fileName), Paths.get(finalPath), StandardCopyOption.REPLACE_EXISTING);
            logger.info("File moved to folder: {} - FILE: {}", toPath, fileName);
        } catch (IOException e) {
            logger.error("Error moving file to error folder: {}", fileName, e);
        }
    }
}
