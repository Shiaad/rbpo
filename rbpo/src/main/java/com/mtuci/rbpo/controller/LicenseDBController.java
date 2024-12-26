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

import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.service.LicenseDBService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/licenses")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LicenseDBController {

    private final LicenseDBService licenseService;

    @PostMapping
    public ResponseEntity<LicenseDB> save(@RequestBody LicenseDB license) {
        licenseService.save(license);
        return ResponseEntity.ok(license);
    }

    @GetMapping
    public ResponseEntity<List<LicenseDB>> findAll() {
        return ResponseEntity.ok(licenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenseDB> findById(@PathVariable Long id) {
        return licenseService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        licenseService.deleteById(id);
        return ResponseEntity.ok("License deleted successfully");
    }
}
