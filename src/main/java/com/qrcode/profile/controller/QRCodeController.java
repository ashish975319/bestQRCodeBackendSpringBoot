//package com.qrcode.profile.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.qrcode.profile.entity.QRCodeData;
//import com.qrcode.profile.service.QRCodeService;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin("*")
//public class QRCodeController {
//
//	@Autowired
//	private QRCodeService qrCodeService;
//
//	@PostMapping("/generate")
//	public String generateQRCode(@RequestBody QRCodeData qrCodeData) {

//		return qrCodeService.generateQRCode(qrCodeData);
//	}
//
//	@GetMapping("/scanned-data")
//	public QRCodeData getScannedData(@RequestParam String secureKey) {
//		return qrCodeService.getQRCodeData(secureKey);
//	}
//
//}

package com.qrcode.profile.controller;

import com.qrcode.profile.entity.QRCodeData;
import com.qrcode.profile.service.QRCodeService;
import com.qrcode.profile.util.ImageUtils;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateQRCode(
            @RequestParam("name") @NotEmpty String name,
            @RequestParam("landline") String landline,
            @RequestParam("mobile") String mobile,
            @RequestParam("email") String email,
            @RequestParam("website") String website,
            @RequestParam("address") String address,
            @RequestParam("company") String company,
            @RequestParam("designation") String designation,
            @RequestParam("photo") @NotNull MultipartFile photo) {

        try {
            byte[] photoBytes = photo.getBytes();
            byte[] compressedPhotoBytes = ImageUtils.compressImage(photoBytes);
            System.out.println("Compressed Photo Bytes Length: " + compressedPhotoBytes.length);

            QRCodeData qrCodeData = QRCodeData.builder()
                    .name(name)
                    .landline(landline)
                    .mobile(mobile)
                    .email(email)
                    .website(website)
                    .address(address)
                    .company(company)
                    .designation(designation)
                    .photo(compressedPhotoBytes)
                    .build();
            String secureKey = qrCodeService.generateQRCode(qrCodeData);
            return ResponseEntity.ok(secureKey);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the image.");
        }
    }

    @GetMapping("/scanned-data")
    public ResponseEntity<QRCodeData> getScannedData(@RequestParam @NotEmpty String secureKey) {
        QRCodeData qrCodeData = qrCodeService.getQRCodeData(secureKey);
        if (qrCodeData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(qrCodeData);
    }
}
