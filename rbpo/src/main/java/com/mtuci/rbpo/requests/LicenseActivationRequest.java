package com.mtuci.rbpo.requests;

import lombok.Data;

@Data
public class LicenseActivationRequest {
    private String deviceName;
    private String deviceMacAddress;
    private String code;
}
