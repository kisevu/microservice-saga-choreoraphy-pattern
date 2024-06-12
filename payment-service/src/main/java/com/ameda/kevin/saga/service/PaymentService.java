package com.ameda.kevin.saga.service;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.dto.OrderRequest;
import com.ameda.kevin.common.dto.PaymentRequest;
import com.ameda.kevin.common.event.OrderEvent;
import com.ameda.kevin.common.event.PaymentEvent;
import com.ameda.kevin.common.event.PaymentStatus;
import com.ameda.kevin.saga.entity.UserBalance;
import com.ameda.kevin.saga.entity.UserTransaction;
import com.ameda.kevin.saga.repository.UserBalanceRepository;
import com.ameda.kevin.saga.repository.UserTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequest  orderRequest = orderEvent.getOrderRequest();
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(orderRequest.getOrderId())
                .userId(orderRequest.getUserId())
                .amount(orderRequest.getAmount())
                .build();
        return  userBalanceRepository.findById(orderRequest.getUserId())
                .filter(userBalance -> userBalance.getPrice()> orderRequest.getAmount())
                .map(userBalance -> {
                    userBalance.setPrice(userBalance.getPrice()-orderRequest.getAmount());
                    userTransactionRepository.save(UserTransaction
                            .builder()
                            .orderId(orderRequest.getOrderId())
                            .userId(orderRequest.getUserId())
                            . amount(orderRequest.getAmount())
                            .build());
                    return new PaymentEvent(paymentRequest, PaymentStatus.PAYMENT_COMPLETED);
                }).orElse(new PaymentEvent(paymentRequest,PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequest().getOrderId())
                .ifPresent(userTransaction -> {
                    userTransactionRepository.delete(userTransaction);
                    userBalanceRepository.findById(userTransaction.getUserId())
                            .ifPresent(userBalance -> userBalance.setPrice(userBalance.getPrice() + userTransaction.getAmount()));
                });
    }

    @PostConstruct
    public void userBalanceInDB(){
        userBalanceRepository.saveAll(Stream.of(UserBalance.builder().userId(101).price(3000).build(),
                UserBalance.builder().userId(102).price(3000).build(),
                UserBalance.builder().userId(103).price(4200).build(),
                UserBalance.builder().userId(104).price(20000).build(),
                UserBalance.builder().userId(105).price(999).build()).collect(Collectors.toList()));
    }
}
