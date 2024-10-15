package com.challenge.parsers.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
public class ColumnMappingConfig {

    private Properties properties;

    public ColumnMappingConfig() {
    }

    @Value("${columns.file.path}")
    private String configFilePath;

    @PostConstruct
    public void init() {
        properties = new Properties();
        try {
            String content = Files.readString(Paths.get(configFilePath));
            properties.load(new StringReader(content));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
//            if (input == null) {
//                System.out.println("Sorry, unable to find " + configFilePath);
//                return;
//            }
//            properties.load(input);
//            properties.forEach((key, value) -> System.out.println(key + ": " + value));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public int getStart(String key) {
        return Integer.parseInt(properties.getProperty(key + ".start"));
    }

    public int getEnd(String key) {
        return Integer.parseInt(properties.getProperty(key + ".end"));
    }
}