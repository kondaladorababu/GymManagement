package com.app.gymManagement.model;

import java.time.LocalDate;

public class Booking {
    private String memberName;         // Name of the member who made the booking
    private String className;          // Name of the class being booked
    private LocalDate participationDate; // Date the member will attend

    // Getters and Setters
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }
}
