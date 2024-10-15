package com.challenge.parsers.Application.UseCases.Files;

import com.challenge.parsers.Application.UseCases.Files.WriteObjectAsJsonFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;



import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class WriteObjectAsJsonFileTest {
    private WriteObjectAsJsonFile writeObjectAsJsonFile;
    private Logger logger;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        logger = Mockito.mock(Logger.class);
        objectMapper = Mockito.mock(ObjectMapper.class);
        writeObjectAsJsonFile = new WriteObjectAsJsonFile(objectMapper);
    }

    @Test
    void testExecute() {
        String filePath = "test.json";
        Object content = new Object();

        writeObjectAsJsonFile.execute(filePath, content);

        File file = new File(filePath);
        assertTrue(file.exists());

        // Clean up
        file.delete();
    }

    @Test
    void testExecuteWithException() throws IOException {
        String filePath = "/invalid/path/test.json";
        Object content = new Object();

        doThrow(new java.io.FileNotFoundException("No such file or directory")).when(objectMapper).writeValue(any(File.class), any(Object.class));

        writeObjectAsJsonFile.execute(filePath, content);

    }
}