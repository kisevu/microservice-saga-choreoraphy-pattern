package com.ameda.kevin.saga.service;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.dto.OrderRequest;
import com.ameda.kevin.common.event.OrderStatus;
import com.ameda.kevin.saga.entity.PurchaseOrder;
import com.ameda.kevin.saga.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequest orderRequest) {
        PurchaseOrder purchaseOrder = orderRepository
                .save(convertDTOtoEntity(orderRequest));
        orderRequest.setOrderId(purchaseOrder.getId());
        orderStatusPublisher.publishOrderEvent(orderRequest,OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }

    public List<PurchaseOrder> allOrders(){
        return orderRepository.findAll();
    }

    private PurchaseOrder convertDTOtoEntity(OrderRequest dto){
        return PurchaseOrder
                .builder()
                .productId(dto.getProductId())
                .userId(dto.getOrderId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .price(dto.getAmount())
                .build();
    }
}
