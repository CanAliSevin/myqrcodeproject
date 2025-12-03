package com.qrscanapp.qrscanbackend.repository;

import com.qrscanapp.qrscanbackend.model.QrLog; // QrLog modelini import etmeli
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QrLogRepository extends JpaRepository<QrLog, Long> {
    // Belirli bir kullanıcı ve şirket için belirli bir tarih aralığındaki logları getir
    List<QrLog> findByUserIdAndCompanyIdAndScanTimeBetweenOrderByScanTimeAsc(String userId, String companyId, LocalDateTime startDate, LocalDateTime endDate);
}