package com.challenge.parsers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${input.files.path}")
    private String inputFilesPath;

    @Value("${output.files.path}")
    private String outputFilesPath;

    @Value("${interval.to.execute}")
    private long intervalToExecute;

    @Value("@{upload.dir}")
    private String uploadDir;


    public String getInputFilesPath() {
        return inputFilesPath;
    }

    public String getOutputFilesPath() {
        return outputFilesPath;
    }

    public long getIntervalToExecute() {
        return intervalToExecute;
    }

    public String getUploadDir() {
        return uploadDir;
    }


}
