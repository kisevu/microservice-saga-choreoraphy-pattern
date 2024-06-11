package com.ameda.kevin.saga.entity;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTransaction {
    @Id
    private Integer orderId;
    private Integer userId;
    private Integer amount;
}
