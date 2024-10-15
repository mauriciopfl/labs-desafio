package com.challenge.parsers.core.Domain.Entities;

import com.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.challenge.parsers.core.Domain.Exceptions.GenericValueException;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import com.challenge.parsers.core.Domain.ValueObjects.GenericValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    @Test
    void testProductConstructor() throws GenericIdException, GenericValueException {
        String productId = "123";
        String valueString = "100.00";
        Product product = new Product(productId, valueString);

        assertEquals(new GenericId(productId), product.getProductId());
        assertEquals(new GenericValue(valueString), product.getValue());
    }

    @Test
    void testProductConstructorWithInvalidGenericId() {
        String invalidId = "0";
        String valueString = "100.00";
        assertThrows(GenericIdException.class, () -> new Product(invalidId, valueString));
    }

    @Test
    void testProductConstructorWithInvalidGenericValue() {
        String productId = "123";
        String invalidValue = "invalid";
        assertThrows(GenericValueException.class, () -> new Product(productId, invalidValue));
    }



    @Test
    void testToString() throws GenericIdException, GenericValueException {
        String productId = "123";
        String valueString = "100.00";
        Product product = new Product(productId, valueString);

        String expectedString = "Product{productId='123', value=100.00}";
        assertEquals(expectedString, product.toString());
    }
}