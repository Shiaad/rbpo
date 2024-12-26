package com.mtuci.rbpo.service;

import java.util.List;
import java.util.Optional;

import com.mtuci.rbpo.model.DeviceLicenseDB;

public interface DeviceLicenseDBService {
    void save(DeviceLicenseDB deviceLicense);
    List<DeviceLicenseDB> findAll();
    Optional<DeviceLicenseDB> findById(long id);
    List<DeviceLicenseDB> findByDeviceId(long deviceId); // Поиск лицензий по ID устройства
    List<DeviceLicenseDB> findByLicenseId(long licenseId); // Поиск по ID лицензии
    void deleteByDeviceId(long deviceId); // Удалить запись по ID устройства
    void deleteByLicenseId(long licenseId); // Удалить запись по ID лицензии
}
