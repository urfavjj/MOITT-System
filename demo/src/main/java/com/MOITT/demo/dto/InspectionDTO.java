package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Inspection;
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
public class InspectionDTO {
    private Long inspectionId;
    private Date inspectionDate;


    @NotBlank(message = "You can't leave the inspection result to be null")
    @Size(min = 2, max = 200, message = "Inspection result has to be between 2 to 200 characters")
    private String inspectionResult;

    @Size(max = 1000, message = "Inspection notes cannot exceed 1000 characters")
    private String inspectionNotes;

    public static InspectionDTO convertToDTO(Inspection entity){
        InspectionDTO dto = InspectionDTO.builder()
                .inspectionId(entity.getId())
                .inspectionDate(entity.getInspectionDate())
                .inspectionResult(entity.getResult())
                .inspectionNotes(entity.getNotes())
                .build();
        return dto;
    }

    public static List<InspectionDTO> convertToDTO(List<Inspection> entityList){
        List<InspectionDTO> dtos = new ArrayList<>();
        for(Inspection inspection : entityList){
            dtos.add(convertToDTO(inspection));
        }
        return dtos;
    }
}