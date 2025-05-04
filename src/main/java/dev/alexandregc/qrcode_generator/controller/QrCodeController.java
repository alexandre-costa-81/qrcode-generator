package dev.alexandregc.qrcode_generator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandregc.qrcode_generator.dto.QrCodeGenerateRequest;
import dev.alexandregc.qrcode_generator.dto.QrCodeGenerateResponse;
import dev.alexandregc.qrcode_generator.service.QrCodeGeneratorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request) {
        try {
            QrCodeGenerateResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error generating QR code: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
