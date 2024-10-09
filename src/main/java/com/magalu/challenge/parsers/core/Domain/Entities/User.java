package com.magalu.challenge.parsers.core.Domain.Entities;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    private final GenericId userId;
    private final String name;
    private List<Order> orders = new ArrayList<Order>();

    public User(GenericId userId, String name) throws GenericIdException {
        this.userId = userId;
        this.name = name;
    }

    public User(GenericId userId, String name, ArrayList<Order> orders) throws GenericIdException {
        this.userId = userId;
        this.name = name;
        this.orders = orders;

    }

}
