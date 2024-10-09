package com.magalu.challenge.parsers.core.Domain.Entities;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
class UserTest {

    @Test
    void testUserConstructorWithTwoParameters() throws GenericIdException {
        GenericId userId = new GenericId("123");
        String name = "John Doe";
        User user = new User(userId, name);

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertTrue(user.getOrders().isEmpty());
    }

    @Test
    void testUserConstructorWithThreeParameters() throws GenericIdException {
        GenericId userId = new GenericId("123");
        String name = "John Doe";
        List<Order> orders = new ArrayList<>();
        User user = new User(userId, name, new ArrayList<>(orders));

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(orders, user.getOrders());
    }



    @Test
    void testUserConstructorWithNullOrders() throws GenericIdException {
        GenericId userId = new GenericId("123");
        String name = "John Doe";
        User user = new User(userId, name, null);

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertNull(user.getOrders());
    }

    @Test
    void testUserConstructorWithInvalidGenericId() {
        String invalidId = "0";
        String name = "John Doe";
        assertThrows(GenericIdException.class, () -> new User(new GenericId(invalidId), name));
    }

    @Test
    void testUserConstructorWithInvalidGenericIdAndOrders() {
        String invalidId = "0";
        String name = "John Doe";
        List<Order> orders = new ArrayList<>();
        assertThrows(GenericIdException.class, () -> new User(new GenericId(invalidId), name, new ArrayList<>(orders)));
    }

}