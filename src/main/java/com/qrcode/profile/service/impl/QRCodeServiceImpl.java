package com.qrcode.profile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrcode.profile.entity.QRCodeData;
import com.qrcode.profile.service.QRCodeService;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class QRCodeServiceImpl implements QRCodeService {

	@Autowired
	private com.qrcode.profile.repository.QRCodeRepository qrCodeRepository;

	@Override
	public String generateQRCode(QRCodeData qrCodeData) {
		String secureKey = generateSecureKey();
		qrCodeData.setSecureKey(secureKey);
		qrCodeRepository.save(qrCodeData);
		return secureKey;
	}

	@Override
	public QRCodeData getQRCodeData(String secureKey) {
		return qrCodeRepository.findBySecureKey(secureKey);
	}

	private String generateSecureKey() {
		byte[] randomBytes = new byte[24];
		new SecureRandom().nextBytes(randomBytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	}
}
