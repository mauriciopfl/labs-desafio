package com.challenge.parsers.Application.UseCases.Files;

import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.config.ColumnMappingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadFileAsFileContentDTO {

    @Autowired
    private ColumnMappingConfig columnMappingConfig;

    public List<FileContentDTO> execute(String filePath) {
        List<FileContentDTO> contents = new ArrayList<>();
        try {
            int userIdStart = columnMappingConfig.getStart("user.id");
            int userIdEnd = columnMappingConfig.getEnd("user.id");
            int userNameStart = columnMappingConfig.getStart("user.name");
            int userNameEnd = columnMappingConfig.getEnd("user.name");
            int productIdStart = columnMappingConfig.getStart("product.id");
            int productIdEnd = columnMappingConfig.getEnd("product.id");
            int orderIdStart = columnMappingConfig.getStart("order.id");
            int orderIdEnd = columnMappingConfig.getEnd("order.id");
            int orderValueStart = columnMappingConfig.getStart("order.value");
            int orderValueEnd = columnMappingConfig.getEnd("order.value");
            int orderDateStart = columnMappingConfig.getStart("order.date");
            int orderDateEnd = columnMappingConfig.getEnd("order.date");

            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {


                if (userIdStart >= 0 && userIdEnd <= line.length() && userIdStart < userIdEnd &&
                        userNameStart >= 0 && userNameEnd <= line.length() && userNameStart < userNameEnd &&
                        productIdStart >= 0 && productIdEnd <= line.length() && productIdStart < productIdEnd &&
                        orderIdStart >= 0 && orderIdEnd <= line.length() && orderIdStart < orderIdEnd &&
                        orderValueStart >= 0 && orderValueEnd <= line.length() && orderValueStart < orderValueEnd &&
                        orderDateStart >= 0 && orderDateEnd <= line.length() && orderDateStart < orderDateEnd) {
                    String userId = line.substring(userIdStart, userIdEnd).trim();
                    String userName = line.substring(userNameStart, userNameEnd).trim();
                    String productId = line.substring(productIdStart, productIdEnd).trim();
                    String orderId = line.substring(orderIdStart, orderIdEnd).trim();
                    String orderValue = line.substring(orderValueStart, orderValueEnd).trim();
                    String orderDate = line.substring(orderDateStart, orderDateEnd).trim();
                    FileContentDTO fileContentDTO = new FileContentDTO(userId, userName, productId, orderId, orderValue, orderDate);
                    contents.add(fileContentDTO);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

}
