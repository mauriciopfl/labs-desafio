package com.challenge.parsers.Application.UseCases.Parsing;

import com.challenge.parsers.Application.UseCases.Parsing.ProcessOrder;
import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.core.Domain.Entities.Order;
import com.challenge.parsers.core.Domain.Entities.User;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProcessOrderTest {

    private ProcessOrder processOrder;
    private List<User> users;
    private FileContentDTO fileContentDTO;

    @BeforeEach
    void setUp() {
        processOrder = new ProcessOrder();
        users = new ArrayList<>();
        fileContentDTO = new FileContentDTO();
    }

    @Test
    void testExecute_UserExists_OrderExists() throws Exception {
        User user = mock(User.class);
        Order order = mock(Order.class);
        GenericId userId = new GenericId("1");
        GenericId orderId = new GenericId("2");

        when(user.getUserId()).thenReturn(userId);
        when(user.getOrders()).thenReturn(new ArrayList<>(List.of(order)));
        when(order.getOrderId()).thenReturn(orderId);

        user.setUserId(userId);

        users.add(user);
        fileContentDTO.setUserId("1");
        fileContentDTO.setOrderId("2");
        fileContentDTO.setDate("20210101");

        processOrder.execute(users, fileContentDTO);

        verify(user, times(1)).getOrders();
    }

    @Test
    void testExecute_UserExists_OrderDoesNotExist() throws Exception {
        User user = mock(User.class);
        GenericId userId = new GenericId("1");

        when(user.getUserId()).thenReturn(userId);
        when(user.getOrders()).thenReturn(new ArrayList<>());
        users.add(user);
        fileContentDTO.setUserId("1");
        fileContentDTO.setOrderId("2");
        fileContentDTO.setDate("20210101");

        processOrder.execute(users, fileContentDTO);

        verify(user, times(2)).getOrders();
//        verify(user).getOrders().add(any(Order.class));
    }

    @Test
    void testExecute_UserDoesNotExist() throws Exception {
        fileContentDTO.setUserId("1");
        fileContentDTO.setOrderId("2");

        processOrder.execute(users, fileContentDTO);

        assertTrue(users.isEmpty());
    }
}