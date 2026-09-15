package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
    // Get all active operators
    @Query("SELECT o FROM Operator o WHERE o.isActive=true")
    List<Operator> getAllOperator();



    // Get active operator by id
    @Query("SELECT o FROM Operator o WHERE o.isActive=true AND o.id=:operator")
    Operator getById(@Param("operator") Long id);

    // Count active licenses
    @Query("SELECT COUNT(s) FROM SpectrumLicense s WHERE s.isActive=true AND s.operator.id=:operatorId AND s.status='ACTIVE'")
    Long countActiveLicenses(@Param("operatorId") Long operatorId);

    // Count open complaints
    @Query("SELECT COUNT(c) FROM Complaint c WHERE c.isActive=true AND c.operator.id=:operatorId AND c.status='OPEN'")
    Long countOpenComplaints(@Param("operatorId") Long operatorId);

}
