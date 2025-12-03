package com.qrscanapp.qrscanbackend.controller;

import com.qrscanapp.qrscanbackend.dto.QrScanRequest;
import com.qrscanapp.qrscanbackend.model.QrLog;
import com.qrscanapp.qrscanbackend.service.QrLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr")
public class QrController {

    private final QrLogService qrLogService;

    @Autowired
    public QrController(QrLogService qrLogService) {
        this.qrLogService = qrLogService;
    }

    @PostMapping("/scan")
    public ResponseEntity<QrLog> scanQrCode(@RequestBody QrScanRequest request) {
        try {
            QrLog savedLog = qrLogService.saveQrLog(request);
            return ResponseEntity.ok(savedLog);//Başarıyla kaydedildi
        } catch (Exception e) {
            e.printStackTrace(); // Hata
            return ResponseEntity.status(500).body(null); // Hata durumunda 500 dön
        }
    }
}