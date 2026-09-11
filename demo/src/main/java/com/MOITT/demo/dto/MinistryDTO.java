package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Ministry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinistryDTO {
    private Long ministryId;

    @NotBlank(message = "You can't leave the ministry name to be null")
    @Size(min = 3, max = 100, message = "Ministry name has to be between 3 to 100 characters")
    private String ministryName;

    @NotBlank(message = "You can't leave the ministry address to be null")
    @Size(min = 5, max = 255, message = "Ministry address has to be between 5 to 255 characters")
    private String ministryAddress;

    public static MinistryDTO convertToDTO(Ministry entity){
        MinistryDTO dto = MinistryDTO.builder()
                .ministryId(entity.getId())
                .ministryName(entity.getName())
                .ministryAddress(entity.getAddress())
                .build();
        return dto;
    }

    public static List<MinistryDTO> convertToDTO(List<Ministry> entityList){
        List<MinistryDTO> dtos = new ArrayList<>();
        for(Ministry ministry : entityList){
            dtos.add(convertToDTO(ministry));
        }

        return dtos;
    }
}
