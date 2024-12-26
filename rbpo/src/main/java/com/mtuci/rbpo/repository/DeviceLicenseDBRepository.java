package com.mtuci.rbpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.DeviceLicenseDB;

@Repository
public interface DeviceLicenseDBRepository extends JpaRepository<DeviceLicenseDB, Long> {
    List<DeviceLicenseDB> findByDeviceId(Long deviceId);
    List<DeviceLicenseDB> findByLicenseId(Long licenseId);
}
