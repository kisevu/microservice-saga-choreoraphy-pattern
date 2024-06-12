package com.ameda.kevin.saga.config;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.dto.OrderRequest;
import com.ameda.kevin.common.event.OrderStatus;
import com.ameda.kevin.common.event.PaymentStatus;
import com.ameda.kevin.saga.entity.PurchaseOrder;
import com.ameda.kevin.saga.repository.OrderRepository;
import com.ameda.kevin.saga.service.OrderStatusPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderStatusUpdateEventHandler {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;
    /*
    * publish event if unsuccessful
    * */

    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> purchaseOrderConsumer){
        orderRepository.findById(id)
                .ifPresent(purchaseOrderConsumer.andThen(this::updateOrder));
    }

    /*
    * Check payment status, if completed mark the order as completed
    * else cancel
    * */

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isCompleted =
                PaymentStatus
                        .PAYMENT_COMPLETED
                        .equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isCompleted ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if(!isCompleted){
            this.orderStatusPublisher.publishOrderEvent(entityToDTO(purchaseOrder),orderStatus);
        }
    }

    public OrderRequest entityToDTO(PurchaseOrder purchaseOrder){
        return OrderRequest.builder()
                .orderId(purchaseOrder.getId())
                .userId(purchaseOrder.getUserId())
                .amount(purchaseOrder.getPrice())
                .productId(purchaseOrder.getProductId())
                .build();
    }
}
