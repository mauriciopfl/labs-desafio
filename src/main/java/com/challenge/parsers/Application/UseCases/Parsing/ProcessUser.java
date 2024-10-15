package com.challenge.parsers.Application.UseCases.Parsing;

import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import com.challenge.parsers.core.Domain.Entities.User;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessUser {
    private static final Logger logger = LoggerFactory.getLogger(ProcessUser.class);

    public void execute(List<User> users, FileContentDTO fileContentDTO) {
        try {
            GenericId id = new GenericId(fileContentDTO.getUserId());
            User user = users.stream().filter(u -> u.getUserId().equals(id)).findFirst().orElse(null);
            if (user == null) {
                user = new User(fileContentDTO.getUserId(), fileContentDTO.getUserName());
                users.add(user);
            }
        } catch (Exception e) {
            logger.error("Error processing user: {}", fileContentDTO.getUserId(), e);
        }
    }


}
