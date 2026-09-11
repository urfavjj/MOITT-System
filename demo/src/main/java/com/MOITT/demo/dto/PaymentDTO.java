package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Payment;
import com.MOITT.demo.entities.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long paymentId;

    @Positive(message = "Payment amount must be greater than zero")
    private Double paymentAmount;

    @NotBlank(message = "You can't leave the payment method to be null")
    private String paymentMethod;


    private PaymentStatus paymentStatus;
    private Date paymentPaidDate;


    public static PaymentDTO convertToDTO(Payment entity){
        PaymentDTO dto = PaymentDTO.builder()
                .paymentId(entity.getId())
                .paymentAmount(entity.getAmount())
                .paymentMethod(entity.getMethod())
                .paymentStatus(entity.getStatus())
                .paymentPaidDate(entity.getPaidDate())
                .build();
        return dto;
    }

    public static List<PaymentDTO> convertToDTO(List<Payment> entityList){
        List<PaymentDTO> dtos = new ArrayList<>();
        for(Payment payment : entityList){
            dtos.add(convertToDTO(payment));
        }
        return dtos;
    }
}