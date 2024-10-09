package com.magalu.challenge.parsers.core.Domain.Repositories;

import com.magalu.challenge.parsers.core.Domain.Entities.Order;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericId;


import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
    List<Order> findOneByID(GenericId userId);
}
