package com.qrcode.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qrcode.profile.entity.QRCodeData;
import com.qrcode.profile.service.QRCodeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class QRCodeController {

	@Autowired
	private QRCodeService qrCodeService;

	@PostMapping("/generate")
	public String generateQRCode(@RequestBody QRCodeData qrCodeData) {
		return qrCodeService.generateQRCode(qrCodeData);
	}

	@GetMapping("/scanned-data")
	public QRCodeData getScannedData(@RequestParam String secureKey) {
		return qrCodeService.getQRCodeData(secureKey);
	}
}
