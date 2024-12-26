package com.mtuci.rbpo.requests;

import java.util.UUID;

import lombok.Data;

@Data
public class DeviceCreationRequest {
    private String name;
    private String macAddress;
    private UUID userId;
}
