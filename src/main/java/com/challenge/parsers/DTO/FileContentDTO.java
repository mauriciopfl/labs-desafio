package com.challenge.parsers.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileContentDTO {
    private String userId;
    private String userName;
    private String orderId;
    private String productId;
    private String orderValue;
    private String date;

    public FileContentDTO(String userId, String userName, String productId, String orderId, String orderValue, String date) {
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.date = date;
    }

    public FileContentDTO() {

    }
}
