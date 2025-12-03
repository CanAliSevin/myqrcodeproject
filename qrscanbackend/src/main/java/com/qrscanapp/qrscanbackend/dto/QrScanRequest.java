package com.qrscanapp.qrscanbackend.dto;

import java.time.LocalDateTime;

public class QrScanRequest {
    private String userId;
    private LocalDateTime scanTime;
    private String qrContent;
    private String companyId;

    public QrScanRequest() {
    }


    public QrScanRequest(String userId, LocalDateTime scanTime, String qrContent, String companyId) {
        this.userId = userId;
        this.scanTime = scanTime;
        this.qrContent = qrContent;
        this.companyId = companyId;
    }

    // --- Getter ve Setter Metotları ---
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }

    public String getQrContent() {
        return qrContent;
    }

    public void setQrContent(String qrContent) {
        this.qrContent = qrContent;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
