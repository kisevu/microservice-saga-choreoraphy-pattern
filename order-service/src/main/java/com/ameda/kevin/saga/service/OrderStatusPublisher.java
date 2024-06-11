package com.ameda.kevin.saga.service;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.dto.OrderRequest;
import com.ameda.kevin.common.event.OrderEvent;
import com.ameda.kevin.common.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequest orderRequest,
                                  OrderStatus orderStatus){
        OrderEvent orderEvent  = new OrderEvent(orderStatus,
                orderRequest);
        orderSinks.tryEmitNext(orderEvent);
    }
}
