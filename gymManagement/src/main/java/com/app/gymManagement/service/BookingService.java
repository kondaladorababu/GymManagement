package com.app.gymManagement.service;

import com.app.gymManagement.model.Booking;
import com.app.gymManagement.model.Classes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final List<Booking> bookingList = new ArrayList<>(); // In-memory storage
    private final ClassesService classesService;

    public BookingService(ClassesService classesService) {
        this.classesService = classesService;
    }

    public ResponseEntity<?> createBooking(Booking booking) {
        Classes targetClass = classesService.getClassByName(booking.getClassName());
        if (targetClass == null) {
            return ResponseEntity.badRequest().body("Class not found.");
        }

        if (booking.getParticipationDate().isBefore(targetClass.getStartDate())) {
            return ResponseEntity.badRequest().body("The joining date is before the class start date.");
        }

        if (targetClass.isFull(booking.getParticipationDate())) {
            return ResponseEntity.badRequest().body("The class is full.");
        }

        targetClass.incrementAttendance(booking.getParticipationDate());
        bookingList.add(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    public List<Booking> searchBookingsByMemberName(String memberName) {
        return bookingList.stream()
                .filter(b -> b.getMemberName().equalsIgnoreCase(memberName))
                .collect(Collectors.toList());
    }

    public List<Booking> searchBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingList.stream()
                .filter(b -> !b.getParticipationDate().isBefore(startDate) && !b.getParticipationDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    public List<Booking> searchBookingsByMemberNameAndDateRange(String memberName, LocalDate startDate, LocalDate endDate) {
        return bookingList.stream()
                .filter(b -> b.getMemberName().equalsIgnoreCase(memberName))
                .filter(b -> !b.getParticipationDate().isBefore(startDate) && !b.getParticipationDate().isAfter(endDate))
                .collect(Collectors.toList());
    }
}
