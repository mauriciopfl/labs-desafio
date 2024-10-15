package com.challenge.parsers.core.Domain.Entities;

import com.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.challenge.parsers.core.Domain.Exceptions.GenericValueException;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import com.challenge.parsers.core.Domain.ValueObjects.GenericValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Product {

    @JsonProperty("product_id")
    private final GenericId productId;
    @JsonProperty("value")
    private final GenericValue value;

    public Product(String productId, String valueString) throws GenericIdException, GenericValueException {
        this.productId = new GenericId(productId);
        this.value = new GenericValue(valueString);
    }

    public Product(GenericId productId, GenericValue value) throws GenericIdException, GenericValueException {
        this.productId = productId;
        this.value = value;
    }
//
//    public String getValueFormatted() {
//        return value.formatted();
//    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", value=" + value +
                '}';
    }
}