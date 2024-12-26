package com.mtuci.rbpo.service.impl;

import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.repository.LicenseDBRepository;
import com.mtuci.rbpo.service.LicenseDBService;
import com.mtuci.rbpo.util.EntityCheck;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LicenseDBServiceImpl implements LicenseDBService {

    private final LicenseDBRepository licenseDBRepository;

    @Override
    public void save(LicenseDB license) {
        licenseDBRepository.save(license);
    }

    @Override
    public List<LicenseDB> findAll() {
        return licenseDBRepository.findAll();
    }

    @Override
    public Optional<LicenseDB> findById(long id) {
        return licenseDBRepository.findById(id);
    }

    @Override
    public Optional<LicenseDB> findByCode(String code){
        return licenseDBRepository.findByCode(code);
    }

    @Override
    public List<LicenseDB> findByUserId(UUID userId) {
        return licenseDBRepository.findByUserId(userId);
    }

    @Override
    public List<LicenseDB> findByProductId(long productId) {
        return licenseDBRepository.findByProductId(productId);
    }

    @Override
    public List<LicenseDB> findByBlockedStatus(boolean blocked) {
        return licenseDBRepository.findByBlocked(blocked);
    }

    @Override
    public void updateLicenseStatus(long licenseId, boolean status) {
        LicenseDB license = EntityCheck.getEntityOrThrowOptional(licenseDBRepository.findById(licenseId), "License not found for id: " + licenseId);
        license.setBlocked(status);
        licenseDBRepository.save(license);
    }

    @Override
    public void updateExpirationDate(long licenseId, LocalDateTime newDate) {
        LicenseDB license = EntityCheck.getEntityOrThrowOptional(licenseDBRepository.findById(licenseId), "License not found for id: " + licenseId);
        
        license.setEndingDate(newDate);
        licenseDBRepository.save(license);

    }

    @Override
    public void deleteById(long id) {
        licenseDBRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        List<LicenseDB> licenses = findByUserId(userId);
        for (LicenseDB license : licenses) {
            licenseDBRepository.delete(license);
        }
    }
}
