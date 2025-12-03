// src/main/java/com/qrscanapp/qrscanbackend/model/QrLog.java (firstnameId/lastnameId kaldırıldı)
package com.qrscanapp.qrscanbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "qr_logs")
public class QrLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "firstname_id") // <<< KALDIRILDI
    // private String firstnameId;

    // @Column(name = "lastname_id") // <<< KALDIRILDI
    // private String lastnameId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "scan_time", nullable = false)
    private LocalDateTime scanTime;

    @Column(name = "qr_content", nullable = false, length = 500)
    private String qrContent;

    @Column(name = "company_id")
    private String companyId;

    // --- Constructor'lar ---
    public QrLog() {
        // No-args constructor
    }

    // Constructor güncellendi
    public QrLog(Long id, String userId, LocalDateTime scanTime, String qrContent, String companyId) {
        this.id = id;
        this.userId = userId;
        this.scanTime = scanTime;
        this.qrContent = qrContent;
        this.companyId = companyId;
    }

    // --- Getter ve Setter Metotları ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getFirstnameId ve setFirstnameId kaldırıldı
    // getLastnameId ve setLastnameId kaldırıldı

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
