package com.mtuci.rbpo.service.impl;

import com.mtuci.rbpo.model.DeviceLicenseDB;
import com.mtuci.rbpo.repository.DeviceLicenseDBRepository;
import com.mtuci.rbpo.service.DeviceLicenseDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceLicenseDBServiceImpl implements DeviceLicenseDBService {

    private final DeviceLicenseDBRepository deviceLicenseDBRepository;

    @Override
    public void save(DeviceLicenseDB deviceLicense) {
        deviceLicenseDBRepository.save(deviceLicense);
    }

    @Override
    public List<DeviceLicenseDB> findAll() {
        return deviceLicenseDBRepository.findAll();
    }

    @Override
    public Optional<DeviceLicenseDB> findById(long id) {
        return deviceLicenseDBRepository.findById(id);
    }

    @Override
    public List<DeviceLicenseDB> findByDeviceId(long deviceId) {
        return deviceLicenseDBRepository.findByDeviceId(deviceId);
    }

    @Override
    public List<DeviceLicenseDB> findByLicenseId(long licenseId) {
        return deviceLicenseDBRepository.findByLicenseId(licenseId);
    }

    @Override
    public void deleteByDeviceId(long deviceId) {
        List<DeviceLicenseDB> deviceLicenses = findByDeviceId(deviceId);
        for (DeviceLicenseDB record : deviceLicenses) {
            deviceLicenseDBRepository.delete(record);
        }
    }

    @Override
    public void deleteByLicenseId(long licenseId) {
        List<DeviceLicenseDB> deviceLicenses = findByLicenseId(licenseId);
        for (DeviceLicenseDB record : deviceLicenses) {
            deviceLicenseDBRepository.delete(record);
        }
    }
}
