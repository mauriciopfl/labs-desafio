package com.magalu.challenge.parsers.core.Domain.Entities;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericDateException;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericDate;
import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    private final String orderId;
    private final User user;
    private final GenericDate genericDate;
    private final List<Product> products;

    public Order(User user, String orderId, String orderDateString, List<Product> products) throws GenericDateException {
        this.user = user;
        this.orderId = orderId;
        this.genericDate = new GenericDate(orderDateString);
        this.products = products;
    }

    public String getOrderDateFormatted() {
        return genericDate.formatted();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + genericDate +
                ", products=" + products +
                '}';
    }
}