package com.magalu.challenge.parsers.core.Domain.Repositories;


import com.magalu.challenge.parsers.core.Domain.Entities.User;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericId;

import java.util.List;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    List<User> findOneByID(GenericId userId);
}
