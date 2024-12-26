package com.mtuci.rbpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.LicenseTypeDB;

@Repository
public interface LicenseTypeDBRepository extends JpaRepository<LicenseTypeDB, Long> {
    LicenseTypeDB findByName(String name);
}
