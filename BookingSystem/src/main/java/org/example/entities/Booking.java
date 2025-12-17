package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    private String bookingId;
    private User user;
    private Screening screening;
    private double totalAmount;
    private List<Seat> seats;
    private BookingStatus bookingStatus;
    private List<Ticket> tickets;
}
