package com.mtuci.rbpo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.DeviceLicenseDB;
import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.LicenseTypeDB;
import com.mtuci.rbpo.model.ProductDB;
import com.mtuci.rbpo.model.Ticket;
import com.mtuci.rbpo.model.User;
import com.mtuci.rbpo.repository.LicenseDBRepository;
import com.mtuci.rbpo.repository.LicenseTypeDBRepository;
import com.mtuci.rbpo.repository.ProductDBRepository;
import com.mtuci.rbpo.repository.UserDBRepository;
import com.mtuci.rbpo.service.DeviceLicenseDBService;
import com.mtuci.rbpo.service.LicenseActionsService;
import com.mtuci.rbpo.service.LicenseDBService;
import com.mtuci.rbpo.service.LicenseHistoryDBService;
import com.mtuci.rbpo.service.TicketService;
import com.mtuci.rbpo.util.EntityCheck;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LicenseActionsServiceImpl implements LicenseActionsService{

    private final ProductDBRepository productRepository;
    private final UserDBRepository userRepository;
    private final LicenseTypeDBRepository licenseTypeRepository;
    private final LicenseDBRepository licenseRepository;
    private final LicenseHistoryDBService licenseHistoryService;
    private final TicketService ticketService;
    private final DeviceLicenseDBService deviceLicenseService;
    private final LicenseDBService licenseService;

    

    @Transactional
    @Override
    public LicenseDB createLicense(Long productId, UUID userId, Long licenseTypeId) {

        ProductDB product = EntityCheck.getEntityOrThrowOptional(productRepository.findById(productId), "Product not found for ID: " + productId);

        User user = EntityCheck.getEntityOrThrowOptional(userRepository.findById(userId), "User not found for ID: " + userId);

        LicenseTypeDB licenseType = EntityCheck.getEntityOrThrowOptional(licenseTypeRepository.findById(licenseTypeId), "License type not found for ID: " + licenseTypeId);



        StringBuilder description = new StringBuilder();
        description.append(String.format("Тип лицензии: %s\n", licenseType.getName()));
        description.append(String.format("Продукт: %s\n", product.getName()));
        description.append(String.format("Владелец: %s\n", user.getUsername()));
        description.append(String.format("Кол-во устройств: %d\n", 0));

        LicenseDB license = new LicenseDB();
        license.setCode(generateActivationCode());
        license.setFirstActivationDate(LocalDateTime.now());
        license.setEndingDate(calculateExpirationDate(licenseType));
        license.setBlocked(false);
        license.setDeviceCount(1);
        license.setDuration(licenseType.getDefaultDuration());
        license.setDescription(description.toString());
        license.setUser(user);
        license.setLicenseType(licenseType);
        license.setProduct(product);

        licenseRepository.save(license);

        licenseHistoryService.recordLicenseChange(license, user, "Created", description.toString());

        return license;
    }

    @Transactional
    @Override
    public Ticket activateLicense(String code, DeviceDB device, User user){
        
        LicenseDB license = EntityCheck.getEntityOrThrowOptional(licenseService.findByCode(code), "License not found for code: " + code);

        if(user.getId() == license.getUser().getId()){
            DeviceLicenseDB deviceLicense = new DeviceLicenseDB();
            licenseRepository.save(license);
            deviceLicense.setDevice(device);
            deviceLicense.setLicense(license);
            deviceLicenseService.save(deviceLicense);
            licenseHistoryService.recordLicenseChange(license, user, "Device with id:" + device.getId(), "Activation suceed");
            return ticketService.createTicket(license, device, "Activated");
        }
        else{
            throw new RuntimeException("Can't activate license");
        }
    }

    private String generateActivationCode() {
        Random random = new Random();

        return Integer.toString(random.nextInt(100));
    }

    private LocalDateTime calculateExpirationDate(LicenseTypeDB licenseType) {
        return LocalDateTime.now().plusDays(licenseType.getDefaultDuration());
    }


    @Override
        public boolean validateLicense(LicenseDB license, User user) {
        /*
        Проверка невыполнения всех условий:
            1. Лицензция заблокирована
            2. Лицензия принадлежит другому пользователю
            3. Лицензия на данное устройство уже активирована
            4. Достигнуто максимальное кол-во устройств для активации
            5. Срок действия лицензии истек
        */
        return (license.getBlocked() &&
                (LocalDateTime.now().isBefore(license.getEndingDate())));
    }

    @Override
        public List<LicenseDB> getActiveLicensesForDevice(DeviceDB device, User user) {

            return device.getDeviceLicenses().stream()
                    .map(DeviceLicenseDB::getLicense)
                    .filter(license ->
                            (license.getUser().getId().equals(user.getId())) &&
                                    !license.getBlocked() &&
                                    (LocalDateTime.now().isBefore(license.getEndingDate()))
                    ).toList();
        }



    @Override
    public List<Ticket> licenseRenewal(String code, User user) {
        LicenseDB license = EntityCheck.getEntityOrThrowOptional(licenseRepository.findByCode(code), "License not found for code: " + code);

        List<Ticket> tickets = license.getDeviceLicenses().stream()
                .map(deviceLicense -> ticketService.createTicket(license, deviceLicense.getDevice(), "")).toList();

        if (validateLicense(license, user))
        {
            throw new RuntimeException("Can't renew license");
        }

        license.setDuration(license.getLicenseType().getDefaultDuration());
        license.setEndingDate(calculateExpirationDate(license.getLicenseType()));

        licenseRepository.save(license);

        tickets.forEach(ticket -> {
            ticket.setDigitalSignature("License succesfully renewed");
            licenseHistoryService.recordLicenseChange(license, user, "Modificated", license.getDescription());
        });
        return tickets;
    }
}