package com.ameda.kevin.saga.config;
/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.event.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderPublisherConfig {

    @Bean
    public Sinks.Many<OrderEvent> orderSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(
            Sinks.Many<OrderEvent> sinks
    ){
        return sinks::asFlux;
    }
}
