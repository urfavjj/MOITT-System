package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Document;
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
public class DocumentDTO {
    private Long documentId;

    @NotBlank(message = "You can't leave the document title to be null")
    @Size(min = 3, max = 200, message = "Document title has to be between 3 to 200 characters")
    private String documentTitle;


    @NotBlank(message = "You can't leave the document type to be null")
    @Size(min = 2, max = 100, message = "Document type has to be between 2 to 100 characters")
    private String documentType;


    private Date documentUploadDate;


    public static DocumentDTO convertToDTO(Document entity){
        DocumentDTO dto = DocumentDTO.builder()
                .documentId(entity.getId())
                .documentTitle(entity.getTitle())
                .documentType(entity.getType())
                .documentUploadDate(entity.getUploadDate())
                .build();
        return dto;
    }



    public static List<DocumentDTO> convertToDTO(List<Document> entityList){
        List<DocumentDTO> dtos = new ArrayList<>();
        for(Document document : entityList){
            dtos.add(convertToDTO(document));
        }
        return dtos;
    }
}