package com.mtuci.rbpo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.User;

@Repository
public interface DeviceDBRepository extends JpaRepository<DeviceDB, Long> {
    List<DeviceDB> findByUserId(UUID userId);
    Optional<DeviceDB> findByMacAddress(String macAddress);
    Optional<DeviceDB> findByNameAndMacAddressAndUser(String name, String mac_address, User user);
}
