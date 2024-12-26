package com.mtuci.rbpo.requests;
import java.util.UUID;

import lombok.Data;

@Data
public class LicenseCreationRequest {
    private Long productId;
    private UUID userId;
    private Long licenseTypeId;
}
