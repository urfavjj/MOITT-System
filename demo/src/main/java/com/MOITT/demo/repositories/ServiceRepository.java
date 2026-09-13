package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    // Get all active services
    @Query("SELECT s FROM Service s WHERE s.isActive=true")
    List<Service> getAllService();



    // Get active service by id
    @Query("SELECT s FROM Service s WHERE s.isActive=true AND s.id=:service")
    Service getById(@Param("service") Long id);
}
