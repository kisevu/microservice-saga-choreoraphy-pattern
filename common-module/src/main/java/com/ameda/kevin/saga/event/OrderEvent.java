package com.ameda.kevin.saga.event;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.saga.dto.OrderRequest;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class OrderEvent implements Event{
    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();

    private OrderRequest orderRequest;
    private OrderStatus orderStatus;

    @Override
    public UUID getEVentId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public OrderEvent(OrderStatus orderStatus,
                      OrderRequest orderRequest) {
        this.orderStatus = orderStatus;
        this.orderRequest = orderRequest;
    }
}
