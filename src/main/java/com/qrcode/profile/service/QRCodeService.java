package com.qrcode.profile.service;

import com.qrcode.profile.entity.QRCodeData;

public interface QRCodeService {
    String generateQRCode(QRCodeData qrCodeData);
    QRCodeData getQRCodeData(String secureKey);
}
