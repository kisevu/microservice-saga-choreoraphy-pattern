package com.ameda.kevin.common.dto;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private Integer orderId;
    private Integer userId;
    private Integer amount;
}
