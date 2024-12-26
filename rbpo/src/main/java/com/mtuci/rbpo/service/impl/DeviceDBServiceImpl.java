package com.mtuci.rbpo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.repository.DeviceDBRepository;
import com.mtuci.rbpo.service.DeviceDBService;
import com.mtuci.rbpo.util.EntityCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceDBServiceImpl implements DeviceDBService {

    private final DeviceDBRepository deviceRepository;

    @Override
    public void save(DeviceDB device) {
        deviceRepository.save(device);
    }

    @Override
    public List<DeviceDB> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<DeviceDB> findById(long id) {
        return deviceRepository.findById(id);
    }

    @Override
    public List<DeviceDB> findByUserId(UUID userId) {
        return deviceRepository.findByUserId(userId);
    }

    @Override
    public Optional<DeviceDB> findByMacAddress(String macAddress) {
        return deviceRepository.findByMacAddress(macAddress);
    }

    @Override
    public Optional<DeviceDB> findDeviceByInfo(String name, String mac_address, User user) {
        return deviceRepository.findByNameAndMacAddressAndUser(name, mac_address, user);
    }
    @Override
    public void updateDeviceName(long deviceId, String newName) {
        
        DeviceDB device = EntityCheck.getEntityOrThrowOptional(deviceRepository.findById(deviceId), "Device not found for id: " + deviceId);
        deleteById(deviceId);
        device.setName(newName);
        deviceRepository.save(device);
        
    }

    @Override
    public void updateMacAddress(long deviceId, String newMacAddress) {
        DeviceDB device = EntityCheck.getEntityOrThrowOptional(deviceRepository.findById(deviceId), "Device not found for id: " + deviceId);
        deleteById(deviceId);
        device.setMacAddress(newMacAddress);
        deviceRepository.save(device);
        
    }
    @Override
    public void deleteById(long id) {
        deviceRepository.deleteById(id);
    }
}
