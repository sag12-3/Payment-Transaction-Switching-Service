package com.sagar.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.ResponseEntity.PaymentTransaction;

import java.util.UUID;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, UUID> {
}

