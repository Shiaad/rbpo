package com.mtuci.rbpo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.Ticket;
import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.requests.CodeRequest;
import com.mtuci.rbpo.requests.DeviceCreationRequest;
import com.mtuci.rbpo.requests.LicenseActivationRequest;
import com.mtuci.rbpo.requests.LicenseCreationRequest;
import com.mtuci.rbpo.service.DeviceDBService;
import com.mtuci.rbpo.service.LicenseActionsService;
import com.mtuci.rbpo.service.TicketService;
import com.mtuci.rbpo.util.AuthUtil;
import com.mtuci.rbpo.util.EntityCheck;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/licenses")
@RequiredArgsConstructor
public class LicenseActionsController {

    private final LicenseActionsService licenseActionsService;
    private final DeviceDBService deviceService;
    private final TicketService ticketService;
    private final AuthUtil authUtil;

    @PostMapping
    @RequestMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LicenseDB> createLicense(@RequestBody LicenseCreationRequest request) {
        LicenseDB createdLicense = licenseActionsService.createLicense(
                request.getProductId(),
                request.getUserId(),
                request.getLicenseTypeId()
        );
        return ResponseEntity.ok(createdLicense);
    }

    @PostMapping
    @RequestMapping("/activate")
    public ResponseEntity<Ticket> activateLicense(@RequestHeader("Authorization") String auth, @RequestBody  LicenseActivationRequest request){
        User user = authUtil.getUserFromAuth(auth);
        DeviceDB newDevice = EntityCheck.getEntityOrThrowOptional(deviceService.findByMacAddress(request.getDeviceMacAddress()), "Device npt found");
        Ticket ticket = licenseActionsService.activateLicense(request.getCode(), newDevice, user);

        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    @RequestMapping("/info")
    public ResponseEntity<?> getLicenseInfo(@RequestHeader("Authorization") String auth, @RequestBody DeviceCreationRequest request) {
        User user = authUtil.getUserFromAuth(auth);
        DeviceDB device = EntityCheck.getEntityOrThrowOptional(deviceService.findDeviceByInfo(request.getName(), request.getMacAddress(), user), "Device not found for user: " + user.getUsername());
        List<LicenseDB> licenses = licenseActionsService.getActiveLicensesForDevice(device, user);
        List<Ticket> tickets = licenses.stream().map(license -> ticketService.createTicket(license, device, "Licenses for current device: ")).toList();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    @RequestMapping("/update")
    public ResponseEntity<?> licenseUpdate(@RequestHeader("Authorization") String auth, @RequestBody CodeRequest request) {
        String code = request.getCode();
        User user = authUtil.getUserFromAuth(auth);
        List<Ticket> tickets = licenseActionsService.licenseRenewal(code, user);
        return ResponseEntity.ok(tickets);
    
    }

}
