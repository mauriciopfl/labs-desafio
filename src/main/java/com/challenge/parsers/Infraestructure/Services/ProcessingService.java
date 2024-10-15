package com.challenge.parsers.Infraestructure.Services;

import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.Application.UseCases.Files.ReadFileAsFileContentDTO;
import com.challenge.parsers.Application.UseCases.Files.WriteObjectAsJsonFile;
import com.challenge.parsers.Application.UseCases.Files.MoveFileToPathWithDateSubFolder;
import com.challenge.parsers.Application.UseCases.Parsing.ProcessOrder;
import com.challenge.parsers.Application.UseCases.Parsing.ProcessProduct;
import com.challenge.parsers.Application.UseCases.Parsing.ProcessUser;
import com.challenge.parsers.core.Domain.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingService.class);

    @Autowired
    private ReadFileAsFileContentDTO fileReaderService;
    @Autowired
    private WriteObjectAsJsonFile writeObjectAsJsonFile;
    @Autowired
    private MoveFileToPathWithDateSubFolder moveFileToPathWithDateSubFolder;

    @Autowired
    private ProcessUser processUser;
    @Autowired
    private ProcessOrder processOrder;
    @Autowired
    private ProcessProduct processProduct;

    @Value("${input.files.path}")
    private String inputFilesPath;

    @Value("${output.files.path}")
    private String outputFilesPath;

    @Value("${processed.files.path}")
    private String processedFilesPath;

    @Value("${error.files.path}")
    private String errorFilesPath;

    public List<String> processFilesFromPath() {
        logger.info("Starting to process files from path: {}", inputFilesPath);
        try {
            Files.createDirectories(Paths.get(inputFilesPath));
        } catch (Exception e) {
            logger.error("Error creating input path: {}", inputFilesPath, e);
        }
        try {
            Long totalFiles = Files.walk(Paths.get(inputFilesPath)).filter(Files::isRegularFile).count();
            logger.info("TOTAL FILES FOUND: {}", totalFiles);
        } catch (IOException e) {
            logger.error("Error counting files from path: {}", inputFilesPath, e);
        }
        try (Stream<Path> paths = Files.walk(Paths.get(inputFilesPath))) {
            List<String> fileResults = new ArrayList<>();
            List<String> fileNames = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            for (String fileName : fileNames) {
                try {
                    processFile(fileName);
                    fileResults.add(fileName + " - PROCESSED");
                } catch (Exception e) {
                    logger.error("Error processing file: {}", fileName, e);
                    fileResults.add(fileName + " - ERROR");

                }
            }
            return fileResults;
        } catch (IOException e) {
            logger.error("Error processing files from path: {}", inputFilesPath, e);
        }
        return new ArrayList<>();
    }

    public void processFile(String fileName) {
        logger.info("Processing file: {}", fileName);

        try {
            List<User> users = new ArrayList<>();

            List<FileContentDTO> content = fileReaderService.execute(fileName);


            for (FileContentDTO fileContentDTO : content) {
                processUser.execute(users, fileContentDTO);
                processOrder.execute(users, fileContentDTO);
                processProduct.execute(users, fileContentDTO);
            }


            if (users.isEmpty()) {
                logger.info("No users found in file: {} or file is invalid format!", fileName);
                moveFileToPathWithDateSubFolder.execute(fileName, errorFilesPath);
            } else {
                String outputFileName = outputFilesPath + "/" + new File(fileName).getName() + ".json";
                Files.createDirectories(Paths.get(outputFilesPath));
                writeObjectAsJsonFile.execute(outputFileName, users);
                logger.info("File JSON output: {}", outputFileName);
                moveFileToPathWithDateSubFolder.execute(fileName, processedFilesPath);
                logger.info("File processed and moved to: {}", processedFilesPath);
            }
        } catch (Exception e) {
            logger.error("Error processing file: {}", fileName, e);
            e.printStackTrace();
        }
    }
}