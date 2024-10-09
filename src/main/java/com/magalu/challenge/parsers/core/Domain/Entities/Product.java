package com.magalu.challenge.parsers.core.Domain.Entities;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericValueException;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericId;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericValue;

import lombok.Getter;

@Getter
public class Product {

    private final GenericId productId;
    private final GenericValue value;

    public Product(String productId, String valueString) throws GenericIdException, GenericValueException {
        this.productId = new GenericId(productId);
        this.value = new GenericValue(valueString);
    }

    public Product(GenericId productId, GenericValue value) throws GenericIdException, GenericValueException {
        this.productId =  productId ;
        this.value = value;
    }

    public String getValueFormatted() {
        return value.formatted();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", value=" + value +
                '}';
    }
}