package com.ameda.kevin.saga.dto;/*
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
public class OrderRequest {
    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
}
