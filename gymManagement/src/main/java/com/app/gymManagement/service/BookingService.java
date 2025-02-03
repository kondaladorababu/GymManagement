package com.app.gymManagement.service;

import com.app.gymManagement.model.Booking;
import com.app.gymManagement.model.Classes;
import com.app.gymManagement.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final ClassesService classesService;
    private final BookingRepository bookingRepository;

    public BookingService(ClassesService classesService, BookingRepository bookingRepository) {
        this.classesService = classesService;
        this.bookingRepository = bookingRepository;
    }

    public ResponseEntity<?> createBooking(Booking booking) {
        Classes targetClass = classesService.getClassByName(booking.getClassName());
        if (targetClass == null) {
            return ResponseEntity.badRequest().body("Class not found.");
        }

        if (booking.getParticipationDate().isBefore(targetClass.getStartDate()) || booking.getParticipationDate().isAfter(targetClass.getEndDate())) {
            return ResponseEntity.badRequest().body("The joining date is not within the class date range.");
        }

        if (targetClass.isFull(booking.getParticipationDate())) {
            return ResponseEntity.badRequest().body("The class is full.");
        }

        targetClass.incrementAttendance(booking.getParticipationDate());
        bookingRepository.addBooking(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    public List<Booking> searchBookingsByMemberName(String memberName) {
        return bookingRepository.getBookingsByMemberName(memberName);
    }

    public List<Booking> searchBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.getBookingsByDateRange(startDate, endDate);
    }

    public List<Booking> searchBookingsByMemberNameAndDateRange(String memberName, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.getBookingsByMemberNameAndDateRange(memberName, startDate, endDate);
    }

}
