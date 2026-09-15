package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Get all active complaints
    @Query("SELECT c FROM Complaint c WHERE c.isActive=true")
    List<Complaint> getAllComplaint();

    // Get active complaint by id
    @Query("SELECT c FROM Complaint c WHERE c.isActive=true AND c.id=:complaint")
    Complaint getById(@Param("complaint") Long id);

    // Get open complaints
    @Query("SELECT c FROM Complaint c WHERE c.isActive=true AND c.status='OPEN'")
    List<Complaint> getOpenComplaints();

    // Get open complaints by operator
    @Query("SELECT c FROM Complaint c WHERE c.isActive=true AND c.status='OPEN' AND c.operator.id=:operatorId")
    List<Complaint> getOpenComplaintsByOperator(
            @Param("operatorId") Long operatorId
    );
}