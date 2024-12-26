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

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.requests.DeviceCreationRequest;
import com.mtuci.rbpo.service.DeviceDBService;
import com.mtuci.rbpo.service.UserDBService;
import com.mtuci.rbpo.util.EntityCheck;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceDBController {

    private final DeviceDBService deviceService;
    private final UserDBService userService;

    @PostMapping
    public ResponseEntity<DeviceDB> save(@RequestBody DeviceCreationRequest request) {
        DeviceDB device = new DeviceDB();
        device.setName(request.getName());
        device.setMacAddress(request.getMacAddress());
        device.setUser(EntityCheck.getEntityOrThrowOptional(userService.findById(request.getUserId()), "User not found"));
        deviceService.save(device);
        return ResponseEntity.ok(device);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DeviceDB>> findAll() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDB> findById(@PathVariable Long id) {
        return deviceService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        deviceService.deleteById(id);
        return ResponseEntity.ok("Device deleted successfully");
    }
}
