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

import com.mtuci.rbpo.model.LicenseTypeDB;
import com.mtuci.rbpo.service.LicenseTypeDBService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/license-types")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LicenseTypeDBController {

    private final LicenseTypeDBService licenseTypeService;

    @PostMapping
    public ResponseEntity<LicenseTypeDB> save(@RequestBody LicenseTypeDB type) {
        licenseTypeService.save(type);
        return ResponseEntity.ok(type);
    }

    @GetMapping
    public ResponseEntity<List<LicenseTypeDB>> findAll() {
        return ResponseEntity.ok(licenseTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenseTypeDB> findById(@PathVariable Long id) {
        return licenseTypeService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        licenseTypeService.deleteById(id);
        return ResponseEntity.ok("License type deleted successfully");
    }
}
