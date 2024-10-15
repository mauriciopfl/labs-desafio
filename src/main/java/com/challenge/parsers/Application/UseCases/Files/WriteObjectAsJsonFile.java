package com.challenge.parsers.Application.UseCases.Files;

import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class WriteObjectAsJsonFile {
    private static final Logger logger = LoggerFactory.getLogger(WriteObjectAsJsonFile.class);

    private final ObjectMapper objectMapper;

    public WriteObjectAsJsonFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void execute(String filePath, Object content) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            objectMapper.writeValue(new File(filePath), content);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error writing object as JSON: {}", filePath, e);
        }
    }
}
