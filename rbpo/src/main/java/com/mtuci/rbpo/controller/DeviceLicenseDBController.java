package com.mtuci.rbpo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtuci.rbpo.model.DeviceLicenseDB;
import com.mtuci.rbpo.service.DeviceLicenseDBService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/device-licenses")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DeviceLicenseDBController {

    private final DeviceLicenseDBService deviceLicenseService;

    @PostMapping
    public ResponseEntity<DeviceLicenseDB> save(@RequestBody DeviceLicenseDB deviceLicense) {
        deviceLicenseService.save(deviceLicense);
        return ResponseEntity.ok(deviceLicense);
    }

    @GetMapping
    public ResponseEntity<List<DeviceLicenseDB>> findAll() {
        return ResponseEntity.ok(deviceLicenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceLicenseDB> findById(@PathVariable Long id) {
        return deviceLicenseService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByDeviceId(@PathVariable Long id) {
        deviceLicenseService.deleteByDeviceId(id);
        return ResponseEntity.ok("Device license deleted successfully");
    }
}
