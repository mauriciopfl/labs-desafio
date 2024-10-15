package com.challenge.parsers.core.Domain.Entities;

import com.challenge.parsers.core.Domain.Exceptions.*;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTest {
    private User user;
    private List<Product> products;
    private String orderId;
    private String orderDateString;
    private Order order;

    @BeforeEach
    public void setUp() throws GenericDateException, GenericIdException, GenericValueException, UserValidationException, OrderValidationException {
        user = new User(new GenericId("1233"), "username");
        products = Arrays.asList(new Product("2134", "100.45"), new Product("2134", "100.45"));
        orderId = "1233";
        orderDateString = "20231001";
        order = new Order(user, orderId, orderDateString, products);
    }



 



    @Test
    public void testConstructorWithGenericId() throws GenericIdException, GenericDateException, OrderValidationException {
        GenericId orderId = new GenericId("1233");
        Order order = new Order(user, orderId, orderDateString, products);
        assertNotNull(order);
//        assertEquals(user, order.getUser());
        assertEquals(orderId, order.getOrderId());
        assertEquals(orderDateString, order.getGenericDate().getOriginalDateFormatted());
        assertEquals(products, order.getProducts());
    }

    @Test
    public void testConstructorWithStringId() throws GenericDateException, OrderValidationException, GenericIdException {
        Order order = new Order(user, "1233", orderDateString, products);
        assertNotNull(order);
//        assertEquals(user, order.getUser());
        assertEquals(new GenericId("1233"), order.getOrderId());
        assertEquals(orderDateString, order.getGenericDate().getOriginalDateFormatted());
        assertEquals(products, order.getProducts());
    }

//    @Test
//    public void testGetOrderDateFormatted() {
//        assertEquals("2023-10-01", order.getOrderDateFormatted());
//    }


}