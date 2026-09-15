package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.DocumentDTO;
import com.MOITT.demo.services.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController {
    DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Add API
    @PostMapping("add")
    public Long addDocument(@Valid @RequestBody DocumentDTO dto){
        return documentService.addDocument(
                dto.getDocumentTitle(),
                dto.getDocumentType(),
                dto.getDocumentUploadDate()
        );
    }



    // Get All API
    @GetMapping("getAll")
    public List<DocumentDTO> getAllDocuments(){
        return DocumentDTO.convertToDTO(documentService.getAllDocuments());
    }


    // Get By Id API
    @GetMapping("getById")
    public DocumentDTO getById(@RequestParam Long id){
        return DocumentDTO.convertToDTO(documentService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public DocumentDTO updateDocument(@Valid @RequestBody DocumentDTO dto){
        return DocumentDTO.convertToDTO(
                documentService.updatedDocument(
                        dto.getDocumentId(),
                        dto.getDocumentTitle(),
                        dto.getDocumentType(),
                        dto.getDocumentUploadDate()
                )
        );
    }


    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteDocumentById(@RequestParam Long id){
        return documentService.deleteById(id);
    }
}