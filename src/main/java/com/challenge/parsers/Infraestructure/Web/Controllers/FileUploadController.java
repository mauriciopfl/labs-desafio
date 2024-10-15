package com.challenge.parsers.Infraestructure.Web.Controllers;

import com.challenge.parsers.Application.UseCases.Files.MoveUploadFiles;
import com.challenge.parsers.Application.UseCases.Files.UploadFiles;
import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@OpenAPIDefinition(info=@Info(title="File Upload API", version = "1.0", description = "API to upload files"))
public class FileUploadController {

    @Autowired
    private UploadFiles uploadFilesUseCase;

    @Autowired
    private MoveUploadFiles moveFilesUseCase;

    @Autowired
    private ProcessingService processingService;

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Upload multiple files",
            description = "Allows uploading multiple text files using multipart/form-data")
    public ResponseEntity<String> uploadFile(
            @RequestPart(name = "files", required = true) MultipartFile[] files
    ) {
        try {
            uploadFilesUseCase.execute(files);
            moveFilesUseCase.execute();
            String message = processingService.processFilesFromPath().stream().map(Object::toString).reduce("", (a, b) -> a + b + "\n");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload or move files: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}