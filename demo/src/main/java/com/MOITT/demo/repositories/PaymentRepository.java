package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Get all active payments
    @Query("SELECT p FROM Payment p WHERE p.isActive=true")
    List<Payment> getAllPayment();



    // Get active payment by id
    @Query("SELECT p FROM Payment p WHERE p.isActive=true AND p.id=:payment")
    Payment getById(@Param("payment") Long id);
}
