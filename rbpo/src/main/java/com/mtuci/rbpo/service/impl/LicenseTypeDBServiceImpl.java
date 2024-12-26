package com.mtuci.rbpo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.LicenseTypeDB;
import com.mtuci.rbpo.repository.LicenseTypeDBRepository;
import com.mtuci.rbpo.service.LicenseTypeDBService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LicenseTypeDBServiceImpl implements LicenseTypeDBService {

    private final LicenseTypeDBRepository licenseTypeDBRepository;

    @Override
    public void save(LicenseTypeDB licenseType) {
        licenseTypeDBRepository.save(licenseType);
    }

    @Override
    public List<LicenseTypeDB> findAll() {
        return licenseTypeDBRepository.findAll();
    }

    @Override
    public Optional<LicenseTypeDB> findById(long id) {
        return licenseTypeDBRepository.findById(id);
    }

    @Override
    public LicenseTypeDB findByName(String name) {
        return licenseTypeDBRepository.findByName(name);
    }

    @Override
    public void deleteById(long id) {
        licenseTypeDBRepository.deleteById(id);
    }
}
