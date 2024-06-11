package com.ameda.kevin.common.event;/*
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
