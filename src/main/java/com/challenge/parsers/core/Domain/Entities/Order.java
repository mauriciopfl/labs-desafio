package com.challenge.parsers.core.Domain.Entities;

import com.challenge.parsers.core.Domain.Exceptions.GenericDateException;
import com.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.challenge.parsers.core.Domain.Exceptions.OrderValidationException;
import com.challenge.parsers.core.Domain.ValueObjects.GenericDate;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    @JsonProperty("order_id")
    private final GenericId orderId;

    @Getter
    @JsonProperty("total")
    private double total = 0;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final GenericDate genericDate;

    @JsonProperty("products")
    private final List<Product> products;

    public Order(String userId, String userName, String orderId, String genericDate, List<Product> products) throws Exception {

        if (userId == null) {
            throw new OrderValidationException("User ID validation failed");
        }
        if (orderId == null) {
            throw new OrderValidationException("Order ID validation failed");
        }
        if (genericDate == null) {
            throw new OrderValidationException("Order date validation failed");
        }
        if (products == null) {
            throw new OrderValidationException("Products validation failed");
        }
//        this.user = new User(userId, userName);
        this.orderId = new GenericId(orderId);
        this.genericDate = new GenericDate(genericDate);
        this.products = products;

    }

    public Order(User user, GenericId orderId, GenericDate genericDate, List<Product> products) throws OrderValidationException {
        this.total = total;
        if (user == null) {
            throw new OrderValidationException("User validation failed");
        }
        if (orderId == null) {
            throw new OrderValidationException("Order ID validation failed");
        }
        if (genericDate == null) {
            throw new OrderValidationException("Order date validation failed");
        }
        if (products == null) {
            throw new OrderValidationException("Products validation failed");
        }
//        this.user = user;
        this.orderId = orderId;
        this.genericDate = genericDate;
        this.products = products;
    }

    public Order(User user, GenericId orderId, String orderDateString, List<Product> products) throws GenericDateException, OrderValidationException {
        this.total = total;
        if (user == null) {
            throw new OrderValidationException("User validation failed");
        }
        if (orderId == null) {
            throw new OrderValidationException("Order ID validation failed");
        }
        if (orderDateString == null) {
            throw new OrderValidationException("Order date validation failed");
        }
        if (products == null) {
            throw new OrderValidationException("Products validation failed");
        }
//        this.user = user;
        this.orderId = orderId;
        this.genericDate = new GenericDate(orderDateString);
        this.products = products;

    }

    public Order(User user, String orderId, String orderDateString, List<Product> products) throws GenericDateException, OrderValidationException, GenericIdException {
        this.total = total;
        if (user == null) {
            throw new OrderValidationException("User validation failed");
        }
        if (orderId == null) {
            throw new OrderValidationException("Order ID validation failed");
        }
        if (orderDateString == null) {
            throw new OrderValidationException("Order date validation failed");
        }
        if (products == null) {
            throw new OrderValidationException("Products validation failed");
        }
//        this.user = user;
        this.orderId = new GenericId(orderId);
        this.genericDate = new GenericDate(orderDateString);
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + genericDate +
                ", products=" + products +
                '}';
    }


    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getValue().getValue().doubleValue();
        }
        this.total = total;
        return total;
    }


}