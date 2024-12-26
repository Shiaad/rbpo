package com.mtuci.rbpo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private LocalDateTime nowDate, activationDate, expirationDate;
    private int expiration;
    private UUID userID;
    private Long deviceId;
    private Boolean isBlocked;
    private String digitalSignature;
}