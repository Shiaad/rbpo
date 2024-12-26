package com.mtuci.rbpo.service;

import java.util.List;
import java.util.Optional;

import com.mtuci.rbpo.model.LicenseTypeDB;

public interface LicenseTypeDBService {
    void save(LicenseTypeDB licenseType);
    List<LicenseTypeDB> findAll();
    Optional<LicenseTypeDB> findById(long id);
    LicenseTypeDB findByName(String name); // Поиск типа лицензии по имени
    void deleteById(long id); // Удалить тип лицензии по ID
}
