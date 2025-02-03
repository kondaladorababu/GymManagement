package com.app.gymManagement.repository;

import com.app.gymManagement.model.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingRepository {

    private final List<Booking> bookingList = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookingList.add(booking);
    }

    public List<Booking> getBookings() {
        return bookingList;
    }

    public List<Booking> getBookingsByMemberName(String memberName) {
        return bookingList.stream()
                .filter(b -> b.getMemberName().equalsIgnoreCase(memberName))
                .collect(Collectors.toList());

    }

    public List<Booking> getBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingList.stream()
                .filter(b -> !b.getParticipationDate().isBefore(startDate) && !b.getParticipationDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    public List<Booking> getBookingsByMemberNameAndDateRange(String memberName, LocalDate startDate, LocalDate endDate){
        return bookingList.stream()
                .filter(b -> b.getMemberName().equalsIgnoreCase(memberName))
                .filter(b -> !b.getParticipationDate().isBefore(startDate) && !b.getParticipationDate().isAfter(endDate))
                .collect(Collectors.toList());
    }


}
