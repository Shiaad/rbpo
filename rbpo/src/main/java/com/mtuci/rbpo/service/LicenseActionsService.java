package com.mtuci.rbpo.service;

import java.util.List;
import java.util.UUID;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.Ticket;
import com.mtuci.rbpo.model.User;

public interface LicenseActionsService{

    LicenseDB createLicense(Long productId, UUID userId, Long licenseTypeId);
    Ticket activateLicense(String code, DeviceDB device, User user);
    List<LicenseDB> getActiveLicensesForDevice(DeviceDB device, User user);
    boolean validateLicense(LicenseDB license, User user);
    List<Ticket> licenseRenewal(String code, User user);

}


