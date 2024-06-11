package com.ameda.kevin.saga.dto;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.saga.event.OrderStatus;

public class OrderResponse {

    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
