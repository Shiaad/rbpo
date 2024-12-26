package com.mtuci.rbpo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.User;

public interface DeviceDBService {
    void save(DeviceDB device);
    List<DeviceDB> findAll();
    Optional<DeviceDB>findById(long id);
    List<DeviceDB> findByUserId(UUID userId);
    Optional<DeviceDB> findByMacAddress(String macAddress);
    Optional<DeviceDB> findDeviceByInfo(String name, String mac_address, User user);
    void updateDeviceName(long deviceId, String newName);
    void updateMacAddress(long deviceId, String newMacAddress);
    DeviceDB registerOrUpdateDevice(String nameDevice, String macDevice, User user);
    void deleteById(long id);
}
