package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.DomainRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainRegistrationRepository extends JpaRepository<DomainRegistration, Long> {
    // Get all active domain registrations
    @Query("SELECT d FROM DomainRegistration d WHERE d.isActive=true")
    List<DomainRegistration> getAllDomainRegistration();

    // Get active domain registration by id
    @Query("SELECT d FROM DomainRegistration d WHERE d.isActive=true AND d.id=:abc")
    DomainRegistration getById(@Param("abc") Long id);

    @Query("SELECT d FROM DomainRegistration d WHERE d.isActive=true AND d.domainName=:domainName")
    DomainRegistration findActiveDomain(@Param("domainName") String domainName);
}
