package org.example.controller;

import org.example.entities.Booking;
import org.example.entities.Movie;
import org.example.entities.Screening;
import org.example.entities.Seat;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.example.services.BookingService;
import org.example.services.ScreeningService;

import java.util.List;

@RestController
public class BookingManagementController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ScreeningService screeningService;

    public Booking createBooking(
            User user,
            Screening screening,
            List<Seat> selectedSeats
    ) {
        return bookingService.createBooking(user, screening, selectedSeats);
    }

    public List<Screening> fetchScreeningDetails(Movie movie) {
        return screeningService.fetchScreeningDetails(movie);
    }
}
