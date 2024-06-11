package com.ameda.kevin.common.dto;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.event.OrderStatus;

public class OrderResponse {

    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
