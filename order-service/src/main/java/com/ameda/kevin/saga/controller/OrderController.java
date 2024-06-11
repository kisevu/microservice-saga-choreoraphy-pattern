package com.ameda.kevin.saga.controller;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.dto.OrderRequest;
import com.ameda.kevin.saga.entity.PurchaseOrder;
import com.ameda.kevin.saga.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody  OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @GetMapping
    public List<PurchaseOrder> ordersAll(){
        return orderService.allOrders();
    }
}
