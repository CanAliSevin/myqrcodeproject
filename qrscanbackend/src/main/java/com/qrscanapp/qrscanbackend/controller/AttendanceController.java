package com.qrscanapp.qrscanbackend.controller;

import com.qrscanapp.qrscanbackend.dto.AttendanceRecord;
import com.qrscanapp.qrscanbackend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    private final ObjectMapper objectMapper;

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    //Ör:GET /api/attendance/123/openai?date=2025-07-31T08:00:00
    @GetMapping("/{userId}/{companyId}")
    public ResponseEntity<List<AttendanceRecord>> getDailyAttendanceForUser(
            @PathVariable String userId,
            @PathVariable String companyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date) {

        List<AttendanceRecord> records = attendanceService.getDailyAttendance(userId, companyId, date);
        //logger.info(...) satırı, backend’de loglama (kayıt alma) için kullanılır.
        try {
            String jsonResponse = objectMapper.writeValueAsString(records);
            logger.info("Backend Response JSON for attendance (userId: {}, companyId: {}, date: {}): {}",
                    userId, companyId, date, jsonResponse);
        } catch (Exception e) {
            logger.error("Error converting attendance records to JSON for logging: {}", e.getMessage());
        }

        return ResponseEntity.ok(records);
    }
}
