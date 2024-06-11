package com.ameda.kevin.common.event;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.common.dto.PaymentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PaymentEvent implements Event {
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private PaymentRequest paymentRequest;
    private PaymentStatus paymentStatus;

    @Override
    public UUID getEVentId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public PaymentEvent(PaymentRequest paymentRequest,
                        PaymentStatus paymentStatus) {
        this.paymentRequest = paymentRequest;
        this.paymentStatus = paymentStatus;
    }
}
