package com.ameda.kevin.saga.config;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.event.OrderEvent;
import com.ameda.kevin.common.event.OrderStatus;
import com.ameda.kevin.common.event.PaymentEvent;
import com.ameda.kevin.saga.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class PaymentConsumerConfig {
    private final PaymentService paymentService;

    /*
    * Will consume OrderEvent and after consumption
    * Will produce the PaymentEvent
    * */
    @Bean
    public Function<Flux<OrderEvent>,Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    /*
    * get user  id
    * check balance
    * if eligible deduct hence success else failed not success
    * */
    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
        }else{
            return Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
