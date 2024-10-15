package com.challenge.parsers.Infraestructure.Services;

import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
class FileProcessingSchedulerTest {

    @Mock
    private ProcessingService processingService;

    @InjectMocks
    private FileProcessingScheduler fileProcessingScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(fileProcessingScheduler, "fixedRate", 1000L);
    }

    @Test
    void testProcessFiles() {
        fileProcessingScheduler.processFiles();
        verify(processingService, times(1)).processFilesFromPath();
    }
}