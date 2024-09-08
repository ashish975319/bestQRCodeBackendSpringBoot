package com.qrcode.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qrcode.profile.entity.QRCodeData;

public interface QRCodeRepository extends JpaRepository<QRCodeData, Long> {
    QRCodeData findBySecureKey(String secureKey);
}
