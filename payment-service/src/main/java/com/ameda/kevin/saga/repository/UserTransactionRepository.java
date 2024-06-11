package com.ameda.kevin.saga.repository;/*
*
@author ameda
@project saga-choreography-microservice
*
*/

import com.ameda.kevin.saga.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
}
