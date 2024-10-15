package com.challenge.parsers.Infraestructure.Web.Controllers;

import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manual")
public class ManualExecutionController {

    @Autowired
    private ProcessingService processingService;

    @GetMapping("/execute")
    @Operation(summary = "Execute manual processing",
            description = "Executes the processing of files from the input directory, returning the result of the processing")
    public ResponseEntity<String> executeManualProcessing() {
        try {
            String message = processingService.processFilesFromPath().stream().map(Object::toString).reduce("", (a, b) -> a + b + "\n");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process files: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}