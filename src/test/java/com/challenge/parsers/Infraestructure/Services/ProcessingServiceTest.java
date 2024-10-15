package com.challenge.parsers.Infraestructure.Services;

import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.Application.UseCases.Files.ReadFileAsFileContentDTO;
import com.challenge.parsers.Application.UseCases.Files.WriteObjectAsJsonFile;
import com.challenge.parsers.Application.UseCases.Files.MoveFileToPathWithDateSubFolder;
import com.challenge.parsers.Application.UseCases.Parsing.ProcessOrder;
import com.challenge.parsers.Application.UseCases.Parsing.ProcessProduct;
import com.challenge.parsers.Application.UseCases.Parsing.ProcessUser;
import com.challenge.parsers.core.Domain.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProcessingServiceTest {
    @InjectMocks
    private ProcessingService processingService;

    @Mock
    private ReadFileAsFileContentDTO fileReaderService;
    @Mock
    private WriteObjectAsJsonFile writeObjectAsJsonFile;
    @Mock
    private MoveFileToPathWithDateSubFolder moveFileToPathWithDateSubFolder;
    @Mock
    private ProcessUser processUser;
    @Mock
    private ProcessOrder processOrder;
    @Mock
    private ProcessProduct processProduct;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(processingService, "inputFilesPath", "input/path");
        ReflectionTestUtils.setField(processingService, "outputFilesPath", "output/path");
        ReflectionTestUtils.setField(processingService, "processedFilesPath", "processed/path");
        ReflectionTestUtils.setField(processingService, "errorFilesPath", "error/path");
    }



    @Test
    void testProcessFile() throws Exception {
        String fileName = "input/path/file1.txt";
        List<FileContentDTO> content = new ArrayList<>();
        List<User> users = new ArrayList<>();

        when(fileReaderService.execute(fileName)).thenReturn(content);

        processingService.processFile(fileName);

        verify(fileReaderService, times(1)).execute(fileName);
        verify(moveFileToPathWithDateSubFolder, times(1)).execute(fileName, "error/path");
    }
}