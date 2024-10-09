package com.magalu.challenge.parsers.core.Domain.Repositories;



import com.magalu.challenge.parsers.core.Domain.Entities.Product;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericId;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    List<Product> findAll();
    List<Product> findOneByID(GenericId orderId);
}
