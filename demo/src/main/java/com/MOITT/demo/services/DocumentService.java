package com.MOITT.demo.services;

import com.MOITT.demo.entities.Document;
import com.MOITT.demo.exceptions.ResourceNotFoundException;
import com.MOITT.demo.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }


    //Add service
    public Long addDocument(String title, String type, Date uploadDate) {
        Document document = new Document();
        document.setIsActive(true);
        document.setCreatedDate(new Date());
        document.setTitle(title);
        document.setType(type);
        document.setUploadDate(uploadDate);
        document = documentRepository.save(document);
        return document.getId();
    }


    //Get All service
    public List<Document> getAllDocuments() {
        return documentRepository.getAllDocument();
    }


    //Get By Id service
    public Document getById(Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent() && document.get().getIsActive()) {
            return document.get();
        }

        throw new ResourceNotFoundException(
                "Document not found by id: " + id
        );
    }


    //Update service
    public Document updatedDocument(Long id, String updateTitle, String updateType, Date updateUploadDate) {
        Document documentToUpdate = getById(id);
        documentToUpdate.setUpdatedDate(new Date());
        documentToUpdate.setTitle(updateTitle);
        documentToUpdate.setType(updateType);
        documentToUpdate.setUploadDate(updateUploadDate);
        documentToUpdate = documentRepository.save(documentToUpdate);
        return documentToUpdate;
    }


    //Delete service
    public Boolean deleteById(Long id) {
        Document deleteDocument = getById(id);
        deleteDocument.setIsActive(false);
        deleteDocument.setUpdatedDate(new Date());
        documentRepository.save(deleteDocument);
        return true;
    }
}
