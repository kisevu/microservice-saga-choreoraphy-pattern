package com.ameda.kevin.saga.event;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import java.util.Date;
import java.util.UUID;

public interface Event {
    UUID getEVentId();
    Date getDate();
}
