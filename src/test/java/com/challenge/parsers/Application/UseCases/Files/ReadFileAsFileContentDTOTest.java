package com.challenge.parsers.Application.UseCases.Files;

import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.Application.UseCases.Files.ReadFileAsFileContentDTO;
import com.challenge.parsers.config.ColumnMappingConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReadFileAsFileContentDTOTest {

    @Mock
    private ColumnMappingConfig columnMappingConfig;

    @InjectMocks
    private ReadFileAsFileContentDTO readFileAsFileContentDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws IOException {
        String filePath = "testFile.txt";
        List<String> lines = List.of(
                "12345John Doe67890123456789012345678901234567890"
        );

        when(columnMappingConfig.getStart("user.id")).thenReturn(0);
        when(columnMappingConfig.getEnd("user.id")).thenReturn(5);
        when(columnMappingConfig.getStart("user.name")).thenReturn(5);
        when(columnMappingConfig.getEnd("user.name")).thenReturn(13);
        when(columnMappingConfig.getStart("product.id")).thenReturn(13);
        when(columnMappingConfig.getEnd("product.id")).thenReturn(18);
        when(columnMappingConfig.getStart("order.id")).thenReturn(18);
        when(columnMappingConfig.getEnd("order.id")).thenReturn(23);
        when(columnMappingConfig.getStart("order.value")).thenReturn(23);
        when(columnMappingConfig.getEnd("order.value")).thenReturn(28);
        when(columnMappingConfig.getStart("order.date")).thenReturn(28);
        when(columnMappingConfig.getEnd("order.date")).thenReturn(38);

        Files.write(Paths.get(filePath), lines);

        List<FileContentDTO> result = readFileAsFileContentDTO.execute(filePath);

        assertEquals(1, result.size());
        assertEquals("12345", result.get(0).getUserId());
        assertEquals("John Doe", result.get(0).getUserName());
        assertEquals("67890", result.get(0).getProductId());
        assertEquals("12345", result.get(0).getOrderId());
        assertEquals("67890", result.get(0).getOrderValue());
        assertEquals("1234567890", result.get(0).getDate());
    }
}
