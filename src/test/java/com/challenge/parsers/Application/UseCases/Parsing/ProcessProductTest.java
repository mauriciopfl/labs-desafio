package com.challenge.parsers.Application.UseCases.Parsing;

import com.challenge.parsers.Application.UseCases.Parsing.ProcessProduct;
import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.core.Domain.Entities.Order;
import com.challenge.parsers.core.Domain.Entities.Product;
import com.challenge.parsers.core.Domain.Entities.User;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProcessProductTest {

    private ProcessProduct processProduct;
    private List<User> users;
    private FileContentDTO fileContentDTO;

    @BeforeEach
    void setUp() {
        processProduct = new ProcessProduct();
        users = new ArrayList<>();
        fileContentDTO = new FileContentDTO();
    }

    @Test
    void testExecute() throws Exception {

        User user = new User(new GenericId("1"), "user1");
        Order order = new Order("1", "user1","2" , "20210101", new ArrayList<>());
        user.getOrders().add(order);
        users.add(user);

        fileContentDTO.setUserId("1");
        fileContentDTO.setOrderId("2");
        fileContentDTO.setProductId("1");
        fileContentDTO.setOrderValue("100");
        processProduct.execute(users, fileContentDTO);

        Assertions.assertEquals(1, order.getProducts().size());
        Product product = order.getProducts().get(0);
        Assertions.assertEquals("1", product.getProductId().getId().toString());
        Assertions.assertEquals("100.00", product.getValue().getValue().toString());
    }
}