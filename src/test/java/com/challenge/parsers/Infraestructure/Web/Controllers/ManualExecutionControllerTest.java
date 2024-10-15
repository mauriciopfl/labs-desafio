package com.challenge.parsers.Infraestructure.Web.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ManualExecutionControllerTest {

    @Mock
    private ProcessingService processingService;

    @InjectMocks
    private ManualExecutionController manualExecutionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteManualProcessing_Success() {
        when(processingService.processFilesFromPath()).thenReturn(Collections.singletonList("File processed"));

        ResponseEntity<String> response = manualExecutionController.executeManualProcessing();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File processed\n", response.getBody());
    }

    @Test
    void testExecuteManualProcessing_Failure() {
        when(processingService.processFilesFromPath()).thenThrow(new RuntimeException("Processing error"));

        ResponseEntity<String> response = manualExecutionController.executeManualProcessing();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to process files: Processing error", response.getBody());
    }
}