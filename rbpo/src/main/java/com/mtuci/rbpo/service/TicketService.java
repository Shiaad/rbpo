package com.mtuci.rbpo.service;


import com.mtuci.rbpo.model.DeviceDB;
import com.mtuci.rbpo.model.LicenseDB;
import com.mtuci.rbpo.model.Ticket;

public interface TicketService {
    Ticket createTicket(LicenseDB license, DeviceDB device, String signature);
}
