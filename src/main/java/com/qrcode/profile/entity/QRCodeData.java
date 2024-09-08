package com.qrcode.profile.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qr_code_data")
@Data
public class QRCodeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String landline;

    @Column
    private String mobile;

    @Column
    private String email;

    @Column
    private String website;

    @Column
    private String address;

    @Column
    private String company;

    @Column
    private String designation;

    @Column(nullable = false)
    private String secureKey;

    @Lob
    @Column(name = "imagedata")
    private byte[] photo;
}
