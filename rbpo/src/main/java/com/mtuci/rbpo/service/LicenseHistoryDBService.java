package com.mtuci.rbpo.service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.LicenseHistoryDB;
import com.mtuci.rbpo.model.User;

public interface LicenseHistoryDBService {
    void save(LicenseHistoryDB licenseHistory);
    List<LicenseHistoryDB> findAll();
    Optional<LicenseHistoryDB> findById(long id);
    List<LicenseHistoryDB> findByLicenseId(long licenseId); // Поиск истории по ID лицензии
    List<LicenseHistoryDB> findByUserId(UUID userId); // Поиск истории по ID пользователя
    void deleteByLicenseId(long licenseId); // Удалить историю по ID лицензии
    public void recordLicenseChange(LicenseDB license, User user, String status, String description);
}
