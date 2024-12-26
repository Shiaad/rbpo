package com.mtuci.rbpo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "device_licenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceLicenseDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", nullable = false)
    private DeviceDB device;

    @ManyToOne
    @JoinColumn(name = "license_id", referencedColumnName = "id", nullable = false)
    private LicenseDB license;

    @Column(name = "activation_date")
    private LocalDateTime activationDate;
}
