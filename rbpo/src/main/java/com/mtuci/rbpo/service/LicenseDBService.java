package com.mtuci.rbpo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.mtuci.rbpo.model.LicenseDB;

public interface LicenseDBService {
    void save(LicenseDB license);
    List<LicenseDB> findAll();
    Optional<LicenseDB> findById(long id);
    Optional<LicenseDB> findByCode(String code);
    List<LicenseDB> findByUserId(UUID userId);
    List<LicenseDB> findByProductId(long productId); // Поиск по productId
    List<LicenseDB> findByBlockedStatus(boolean blocked);
    void updateLicenseStatus(long licenseId, boolean status); // Обновить статус лицензии
    void updateExpirationDate(long licenseId, LocalDateTime newDate);
    void deleteById(long id); // Удалить лицензию по ID
    void deleteByUserId(UUID userId);
}
