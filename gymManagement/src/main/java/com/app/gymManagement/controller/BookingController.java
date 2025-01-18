package com.app.gymManagement.controller;

import com.app.gymManagement.model.Booking;
import com.app.gymManagement.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/member")
    public List<Booking> searchBookingsByMemberName(@RequestParam String memberName) {
        return bookingService.searchBookingsByMemberName(memberName);
    }

    @GetMapping("/dateRange")
    public List<Booking> searchBookingsByDateRange(@RequestParam LocalDate startDate , @RequestParam LocalDate endDate) {
        return bookingService.searchBookingsByDateRange(startDate, endDate);
    }

    @GetMapping("/nameAndDate")
    public List<Booking> searchBookingsByMemberNameAndDateRange(@RequestParam String memberName, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return bookingService.searchBookingsByMemberNameAndDateRange(memberName, startDate, endDate);
    }

}
