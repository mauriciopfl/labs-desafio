package com.challenge.parsers.Infraestructure.Web.Controllers;

import com.challenge.parsers.Application.UseCases.Files.MoveUploadFiles;
import com.challenge.parsers.Application.UseCases.Files.UploadFiles;
import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FileUploadControllerTest {
    @Mock
    private UploadFiles uploadFilesUseCase;

    @Mock
    private MoveUploadFiles moveFilesUseCase;

    @Mock
    private ProcessingService processingService;

    @InjectMocks
    private FileUploadController fileUploadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadFile_Success() throws IOException {
        MultipartFile[] files = new MultipartFile[]{mock(MultipartFile.class)};
        when(processingService.processFilesFromPath()).thenReturn(Collections.singletonList("File processed"));

        ResponseEntity<String> response = fileUploadController.uploadFile(files);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File processed\n", response.getBody());
        verify(uploadFilesUseCase, times(1)).execute(files);
        verify(moveFilesUseCase, times(1)).execute();
    }

    @Test
    void testUploadFile_Failure() throws IOException {
        MultipartFile[] files = new MultipartFile[]{mock(MultipartFile.class)};
        doThrow(new IOException("IO Error")).when(uploadFilesUseCase).execute(files);

        ResponseEntity<String> response = fileUploadController.uploadFile(files);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to upload or move files: IO Error", response.getBody());
        verify(uploadFilesUseCase, times(1)).execute(files);
        verify(moveFilesUseCase, times(0)).execute();
    }
}