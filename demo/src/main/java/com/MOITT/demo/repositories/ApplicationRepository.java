package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Get all active applications
    @Query("SELECT a FROM Application a WHERE a.isActive=true")
    List<Application> getAllApplication();



    // Get active application by id
    @Query("SELECT a FROM Application a WHERE a.isActive=true AND a.id=:application")
    Application getById(@Param("application") Long id);
}
