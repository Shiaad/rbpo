package com.mtuci.rbpo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.LicenseHistoryDB;

@Repository
public interface LicenseHistoryDBRepository extends JpaRepository<LicenseHistoryDB, Long> {
    List<LicenseHistoryDB> findByLicenseId(Long licenseId);
    List<LicenseHistoryDB> findByUserId(UUID userId);
}
