package com.challenge.parsers.Application.UseCases.Parsing;

import com.challenge.parsers.DTO.FileContentDTO;
import com.challenge.parsers.Infraestructure.Services.ProcessingService;
import com.challenge.parsers.core.Domain.Entities.Order;
import com.challenge.parsers.core.Domain.Entities.User;
import com.challenge.parsers.core.Domain.ValueObjects.GenericId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessOrder {
    private static final Logger logger = LoggerFactory.getLogger(ProcessOrder.class);

    public void execute(List<User> users, FileContentDTO fileContentDTO) throws Exception {
        try {
            GenericId id = new GenericId(fileContentDTO.getUserId());
            User user = users.stream().filter(u -> u.getUserId().equals(id)).findFirst().orElse(null);
            if (user != null) {
                GenericId orderId = new GenericId(fileContentDTO.getOrderId());
                Order order = user.getOrders().stream().filter(o -> o.getOrderId().equals(orderId)).findFirst().orElse(null);
                if (order == null) {
                    order = new Order(fileContentDTO.getUserId(), fileContentDTO.getUserName(), fileContentDTO.getOrderId(), fileContentDTO.getDate(), new ArrayList<>());
                    user.getOrders().add(order);
                }
            }
        } catch (Exception e) {
            logger.error("Error processing order: {}", fileContentDTO.getUserId(), e);
        }
    }
}
