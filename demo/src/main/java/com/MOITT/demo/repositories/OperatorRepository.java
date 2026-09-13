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
}
