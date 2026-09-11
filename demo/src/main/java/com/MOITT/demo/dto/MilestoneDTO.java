package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Milestone;
import com.MOITT.demo.entities.MilestoneStatus;
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
public class MilestoneDTO {
    private Long milestoneId;

    @NotBlank(message = "You can't leave the milestone title to be null")
    @Size(min = 3, max = 200, message = "Milestone title has to be between 3 to 200 characters")
    private String milestoneTitle;

    private Date milestoneDueDate;
    private MilestoneStatus milestoneStatus;

    public static MilestoneDTO convertToDTO(Milestone entity){
        MilestoneDTO dto = MilestoneDTO.builder()
                .milestoneId(entity.getId())
                .milestoneTitle(entity.getTitle())
                .milestoneDueDate(entity.getDueDate())
                .milestoneStatus(entity.getStatus())
                .build();
        return dto;
    }

    public static List<MilestoneDTO> convertToDTO(List<Milestone> entityList){
        List<MilestoneDTO> dtos = new ArrayList<>();
        for(Milestone milestone : entityList){
            dtos.add(convertToDTO(milestone));
        }
        return dtos;
    }
}