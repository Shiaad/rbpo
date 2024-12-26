package com.mtuci.rbpo.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.LicenseDB;

@Repository
public interface LicenseDBRepository extends JpaRepository<LicenseDB, Long> {
    List<LicenseDB> findByUserId(UUID userId);
    List<LicenseDB> findByProductId(Long productId);
    List<LicenseDB> findByBlocked(boolean blocked);
    Optional<LicenseDB> findByCode(String code);
}
