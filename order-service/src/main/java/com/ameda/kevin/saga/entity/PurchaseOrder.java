package com.ameda.kevin.saga.entity;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.event.OrderStatus;
import com.ameda.kevin.common.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "_tbl_purchase_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
