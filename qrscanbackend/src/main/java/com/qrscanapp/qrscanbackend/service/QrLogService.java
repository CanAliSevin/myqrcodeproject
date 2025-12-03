// src/main/java/com/qrscanapp/qrscanbackend/service/QrLogService.java (firstname/lastname kaldırıldı)
package com.qrscanapp.qrscanbackend.service;

import com.qrscanapp.qrscanbackend.dto.QrScanRequest;
import com.qrscanapp.qrscanbackend.model.QrLog;
import com.qrscanapp.qrscanbackend.repository.QrLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QrLogService {

    private final QrLogRepository qrLogRepository;

    @Autowired
    public QrLogService(QrLogRepository qrLogRepository) {
        this.qrLogRepository = qrLogRepository;
    }

    public QrLog saveQrLog(QrScanRequest request) {
        QrLog qrLog = new QrLog();
        qrLog.setUserId(request.getUserId());
        qrLog.setScanTime(request.getScanTime() != null ? request.getScanTime() : LocalDateTime.now());
        qrLog.setQrContent(request.getQrContent());
        qrLog.setCompanyId(request.getCompanyId());

        return qrLogRepository.save(qrLog);
    }
}
