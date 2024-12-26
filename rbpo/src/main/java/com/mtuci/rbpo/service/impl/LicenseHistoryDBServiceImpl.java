package com.mtuci.rbpo.service.impl;

import com.mtuci.rbpo.model.LicenseHistoryDB;
import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.repository.LicenseHistoryDBRepository;
import com.mtuci.rbpo.service.LicenseHistoryDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LicenseHistoryDBServiceImpl implements LicenseHistoryDBService {

    private final LicenseHistoryDBRepository licenseHistoryDBRepository;

    @Override
    public void save(LicenseHistoryDB licenseHistory) {
        licenseHistoryDBRepository.save(licenseHistory);
    }

    @Override
    public List<LicenseHistoryDB> findAll() {
        return licenseHistoryDBRepository.findAll();
    }

    @Override
    public Optional<LicenseHistoryDB> findById(long id) {
        return licenseHistoryDBRepository.findById(id);
    }

    @Override
    public List<LicenseHistoryDB> findByLicenseId(long licenseId) {
        return licenseHistoryDBRepository.findByLicenseId(licenseId);
    }

    @Override
    public List<LicenseHistoryDB> findByUserId(UUID userId) {
        return licenseHistoryDBRepository.findByUserId(userId);
    }

    @Override
    public void deleteByLicenseId(long licenseId) {
        List<LicenseHistoryDB> history = findByLicenseId(licenseId);
        for (LicenseHistoryDB record : history) {
            licenseHistoryDBRepository.delete(record);
        }
    }

    @Override
    public void recordLicenseChange(LicenseDB license, User user, String status, String description){
        LicenseHistoryDB licenseHistory = new LicenseHistoryDB();
        licenseHistory.setLicense(license);
        licenseHistory.setUser(user);
        licenseHistory.setStatus("Created");
        licenseHistory.setChangeDate(LocalDateTime.now());
        licenseHistory.setDescription(description);

        save(licenseHistory);
    }
    
}
