package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    // Get all active vendors
    @Query("SELECT v FROM Vendor v WHERE v.isActive=true")
    List<Vendor> getAllVendor();



    // Get active vendor by id
    @Query("SELECT v FROM Vendor v WHERE v.isActive=true AND v.id=:vendor")
    Vendor getById(@Param("vendor") Long id);
}
