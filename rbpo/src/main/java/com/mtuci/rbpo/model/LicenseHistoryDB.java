package com.mtuci.rbpo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "license_histories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LicenseHistoryDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "license_id", referencedColumnName = "id", nullable = false)
    private LicenseDB license;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @Column(name = "description")
    private String description;
}
