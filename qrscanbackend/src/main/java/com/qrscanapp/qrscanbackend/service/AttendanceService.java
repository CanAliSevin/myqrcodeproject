package com.qrscanapp.qrscanbackend.service;
import com.qrscanapp.qrscanbackend.dto.AttendanceRecord;


import com.qrscanapp.qrscanbackend.model.QrLog;
import com.qrscanapp.qrscanbackend.repository.QrLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {
    private final QrLogRepository qrLogRepository;

    public static final String APP_QR_PREFIX = "MYQRAPP_SECURE_";


    @Autowired
    public AttendanceService(QrLogRepository qrLogRepository) {
        this.qrLogRepository = qrLogRepository;
    }

    public List<AttendanceRecord> getDailyAttendance(String userId, String companyId, LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(LocalTime.MAX);

        List<QrLog> dailyLogs = qrLogRepository.findByUserIdAndCompanyIdAndScanTimeBetweenOrderByScanTimeAsc(userId, companyId, startOfDay, endOfDay);

        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        LocalDateTime entryTime = null;

        for (QrLog qrLog : dailyLogs) {

            if (entryTime == null) {
                entryTime = qrLog.getScanTime();
            } else {
                LocalDateTime exitTime = qrLog.getScanTime();
                Duration duration = Duration.between(entryTime, exitTime);
                attendanceRecords.add(new AttendanceRecord(entryTime, exitTime, duration, true));
                entryTime = null;
            }
            // }
        }

        if (entryTime != null) {
            attendanceRecords.add(new AttendanceRecord(entryTime, null, null, false));
        }

        return attendanceRecords;
    }


}
