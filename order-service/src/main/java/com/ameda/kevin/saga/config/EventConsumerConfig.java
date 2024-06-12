package com.ameda.kevin.saga.config;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConfig {

    private final OrderStatusUpdateEventHandler orderStatusUpdateEventHandler;


    /*
    * Listen to payment event
    * check payment status
    * if status is completed then proceed to complete the order
    * if failed, cancel the order
    * */
    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return (paymentEvent -> orderStatusUpdateEventHandler.updateOrder(paymentEvent.getPaymentRequest().getOrderId(),
                purchaseOrder -> {
            purchaseOrder.setPaymentStatus(paymentEvent.getPaymentStatus());
                }));
    }


}
