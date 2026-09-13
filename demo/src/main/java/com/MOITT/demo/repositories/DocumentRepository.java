package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Get all active documents
    @Query("SELECT d FROM Document d WHERE d.isActive=true")
    List<Document> getAllDocument();



    // Get active document by id
    @Query("SELECT d FROM Document d WHERE d.isActive=true AND d.id=:document")
    Document getById(@Param("document") Long id);
}
