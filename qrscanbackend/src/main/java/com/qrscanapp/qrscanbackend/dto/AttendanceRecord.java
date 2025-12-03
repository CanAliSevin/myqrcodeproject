package com.qrscanapp.qrscanbackend.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class AttendanceRecord {
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Duration totalWorkDuration;
    private boolean isCompleted;

    // parametresizz constructor
    public AttendanceRecord() {
    }

    public AttendanceRecord(LocalDateTime entryTime, LocalDateTime exitTime, Duration totalWorkDuration, boolean isCompleted) {
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.totalWorkDuration = totalWorkDuration;
        this.isCompleted = isCompleted;
    }

    // --- Getter ve Setter Metotlarımız ---
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Duration getTotalWorkDuration() {
        return totalWorkDuration;
    }

    public void setTotalWorkDuration(Duration totalWorkDuration) {
        this.totalWorkDuration = totalWorkDuration;
    }

    public boolean isCompleted() { // boolean için 'is' kullanılır, 'get' değil
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}