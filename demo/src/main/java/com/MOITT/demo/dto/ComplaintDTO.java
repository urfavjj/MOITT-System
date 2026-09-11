package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Complaint;
import com.MOITT.demo.entities.ComplaintStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class ComplaintDTO {
    private Long complaintId;

    @NotBlank(message = "You can't leave the complaint subject to be null")
    @Size(min = 3, max = 200, message = "Complaint subject has to be between 3 to 200 characters")
    private String complaintSubject;


    @NotBlank(message = "You can't leave the complaint description to be null")
    @Size(min = 10, max = 1000, message = "Complaint description has to be between 10 to 1000 characters")
    private String complaintDescription;


    private ComplaintStatus complaintStatus;
    private Date complaintFiledDate;

    public static ComplaintDTO convertToDTO(Complaint entity){
        ComplaintDTO dto = ComplaintDTO.builder()
                .complaintId(entity.getId())
                .complaintSubject(entity.getSubject())
                .complaintDescription(entity.getDescription())
                .complaintStatus(entity.getStatus())
                .complaintFiledDate(entity.getFiledDate())
                .build();
        return dto;
    }



    public static List<ComplaintDTO> convertToDTO(List<Complaint> entityList){
        List<ComplaintDTO> dtos = new ArrayList<>();
        for(Complaint complaint : entityList){
            dtos.add(convertToDTO(complaint));
        }
        return dtos;
    }
}