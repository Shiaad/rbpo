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

import com.mtuci.rbpo.model.LicenseHistoryDB;
import com.mtuci.rbpo.service.LicenseHistoryDBService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/license-histories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LicenseHistoryDBController {

    private final LicenseHistoryDBService historyService;

    @PostMapping
    public ResponseEntity<LicenseHistoryDB> save(@RequestBody LicenseHistoryDB history) {
        historyService.save(history);
        return ResponseEntity.ok(history);
    }

    @GetMapping
    public ResponseEntity<List<LicenseHistoryDB>> findAll() {
        return ResponseEntity.ok(historyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenseHistoryDB> findById(@PathVariable Long id) {
        return historyService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        historyService.deleteByLicenseId(id);
        return ResponseEntity.ok("License history deleted successfully");
    }
}
