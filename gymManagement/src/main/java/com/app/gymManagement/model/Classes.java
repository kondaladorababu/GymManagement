package com.app.gymManagement.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Classes {
    private String name;                    // Name of the class
    private LocalDate startDate;           // Start date of the class series
    private LocalDate endDate;             // End date of the class series
    private LocalTime startTime;           // Time the class starts
    private int duration;                  // Duration of the class in minutes
    private int capacity;                  // Maximum number of attendees
    private Map<LocalDate, Integer> attendance = new HashMap<>();
    // Tracks attendance per date

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<LocalDate, Integer> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<LocalDate, Integer> attendance) {
        this.attendance = attendance;
    }

    // Increment attendance for a specific date
    public void incrementAttendance(LocalDate date) {
        attendance.put(date, attendance.getOrDefault(date, 0) + 1);
    }

    // Check if the class is full on a specific date
    public boolean isFull(LocalDate date) {
        return attendance.getOrDefault(date, 0) >= capacity;
    }
}
