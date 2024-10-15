package com.challenge.parsers.Application.UseCases.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoveFileToPathWithDateSubFolderTest {

    private MoveFileToPathWithDateSubFolder moveFileToPathWithDateSubFolder;
    private Logger logger;


    @BeforeEach
    void setUp() {
        logger = Mockito.mock(Logger.class);
        moveFileToPathWithDateSubFolder = new MoveFileToPathWithDateSubFolder();

        try {
            Files.createFile(Paths.get("test.txt"));
        } catch (Exception e) {
            logger.error("Error creating file: {}", "test.txt", e);
        }
    }

    @Test
    void testExecute() {
        String fileName = "test.txt";
        String toPath = "test";
        String dateSubfolder = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String finalPath = toPath + "/" + dateSubfolder + "/" + new File(fileName).getName();


        try {
            Files.createDirectories(Paths.get(toPath));
            Files.createDirectories(Paths.get(toPath + "/" + dateSubfolder));
        } catch (Exception e) {
            logger.error("Error creating folder: {}", toPath + "/" + dateSubfolder, e);
        }

        moveFileToPathWithDateSubFolder.execute(fileName, toPath);

        File file = new File(finalPath);
        assertTrue(file.exists());

    }

}