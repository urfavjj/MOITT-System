package com.MOITT.demo.services;

import com.MOITT.demo.entities.Payment;
import com.MOITT.demo.entities.PaymentStatus;
import com.MOITT.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    //Add service
    public Long addPayment(Double amount, String method, PaymentStatus status) {
        Payment payment = new Payment();
        payment.setIsActive(true);
        payment.setCreatedDate(new Date());
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setStatus(status);
        payment = paymentRepository.save(payment);
        return payment.getId();
    }

    //Get All service
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayment();
    }

    //Get By Id service
    public Payment getById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()
                && payment.get().getIsActive()) {

            return payment.get();
        }
        throw new ResourceNotFoundException(
                "Payment not found by id: " + id
        );
    }

    //Update service
    public Payment updatedPayment(Long id, Double updateAmount, String updateMethod, PaymentStatus updateStatus) {
        Payment paymentToUpdate = getById(id);
        paymentToUpdate.setUpdatedDate(new Date());
        paymentToUpdate.setAmount(updateAmount);
        paymentToUpdate.setMethod(updateMethod);
        paymentToUpdate.setStatus(updateStatus);
        paymentToUpdate = paymentRepository.save(paymentToUpdate);
        return paymentToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Payment deletePayment = getById(id);
        deletePayment.setIsActive(false);
        deletePayment.setUpdatedDate(new Date());
        paymentRepository.save(deletePayment);
        return true;
    }
}
