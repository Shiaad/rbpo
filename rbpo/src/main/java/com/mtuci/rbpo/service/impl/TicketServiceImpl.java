package com.mtuci.rbpo.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.Ticket;
import com.mtuci.rbpo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

    @Override
    public Ticket createTicket(LicenseDB license, DeviceDB device, String signature) {

        Ticket ticket = new Ticket(
                                    LocalDateTime.now(),
                                    license.getFirstActivationDate(),
                                    license.getEndingDate(),
                                    3600,
                                    device.getUser().getId(),
                                    device.getId(),
                                    license.getBlocked(),
                                    signature
                                    );
        return ticket;
    }

}


