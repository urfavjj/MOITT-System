package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.PaymentDTO;
import com.MOITT.demo.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {
    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Add API
    @PostMapping("add")
    public Long addPayment(@Valid @RequestBody PaymentDTO dto){
        return paymentService.addPayment(
                dto.getPaymentAmount(),
                dto.getPaymentMethod(),
                dto.getPaymentStatus()
        );
    }



    // Get All API
    @GetMapping("getAll")
    public List<PaymentDTO> getAllPayments(){
        return PaymentDTO.convertToDTO(paymentService.getAllPayments());
    }

    // Get By Id API
    @GetMapping("getById")
    public PaymentDTO getById(@RequestParam Long id){
        return PaymentDTO.convertToDTO(paymentService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public PaymentDTO updatePayment(@Valid @RequestBody PaymentDTO dto){
        return PaymentDTO.convertToDTO(
                paymentService.updatedPayment(
                        dto.getPaymentId(),
                        dto.getPaymentAmount(),
                        dto.getPaymentMethod(),
                        dto.getPaymentStatus()
                )
        );
    }

    //Record API
    @PostMapping("record")
    public Long recordPayment(@RequestParam Long applicationId, @RequestParam Double amount, @RequestParam String method){
        return paymentService.recordPayment(applicationId, amount, method);
    }

    // Delete API
    @DeleteMapping("delete")
    public Boolean deletePaymentById(@RequestParam Long id){
        return paymentService.deleteById(id);
    }
}