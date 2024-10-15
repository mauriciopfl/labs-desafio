package com.challenge.parsers.core.Domain.Entities;

import com.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import com.challenge.parsers.core.Domain.Exceptions.UserValidationException;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
@Getter
public class User {
    @JsonProperty("user_id")
    private GenericId userId;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("orders")
    private List<Order> orders = new ArrayList<Order>();


    public User(String userId, String name) throws GenericIdException, UserValidationException{
        if(userId == null || name == null) {
            throw new UserValidationException("User validation failed");
        }
        this.userId = new GenericId(userId);
        this.name = name;
    }

    public User(GenericId userId, String name) throws GenericIdException, UserValidationException{
        if(userId == null || name == null) {
            throw new UserValidationException("User validation failed");
        }
        this.userId = userId;
        this.name = name;
    }

    public User(GenericId userId, String name, ArrayList<Order> orders) throws GenericIdException, UserValidationException {

        if(userId == null || name == null) {
            throw new UserValidationException("User validation failed");
        }

        this.userId = userId;
        this.name = name;
        this.orders = orders;

    }

    public void setUserId(GenericId userId) throws GenericIdException {
        if(userId == null) {
            throw new GenericIdException("User validation failed");
        }
        this.userId = userId;
    }

}
