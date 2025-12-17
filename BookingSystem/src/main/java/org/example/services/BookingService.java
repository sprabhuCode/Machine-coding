package org.example.services;

import lombok.Data;
import org.example.entities.Booking;
import org.example.entities.BookingStatus;
import org.example.entities.Screening;
import org.example.entities.Seat;
import org.example.entities.Ticket;
import org.example.entities.User;
import org.example.services.strategy.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Data
public class BookingService {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PricingStrategy pricingStrategy;

    // screeningId -> locked seatIds
    private final Map<String, Set<String>> lockedSeatsMap = new ConcurrentHashMap<>();

    public Booking createBooking(User user, Screening screening,
                                 List<Seat> selectedSeats
    ) {
        if (!screeningService.areSeatsAvailable(screening, selectedSeats)) {
            throw new RuntimeException("Selected seats are not available");
        }

        // 2. Lock seats (booking responsibility)
        lockSeats(screening, selectedSeats);

        // 3. Calculate price
        double totalAmount = calculateTotalPrice(screening, selectedSeats);

        // 4. Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setScreening(screening);
        booking.setSeats(selectedSeats);
        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus(BookingStatus.PENDING);

        // 5. Process payment
        boolean paymentSuccess = paymentService.processPayment(totalAmount);

        if (!paymentSuccess) {
            unlockSeats(screening, selectedSeats);
            booking.setBookingStatus(BookingStatus.CANCELLED);
            throw new RuntimeException("Payment failed");
        }

        // 6. Confirm booking & generate tickets
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        generateTickets(booking);

        return booking;

    }

    public Set<String> getLockedSeatsForScreening(String screeningId) {
        return lockedSeatsMap.getOrDefault(screeningId, Set.of());
    }

    private double calculateTotalPrice(Screening screening, List<Seat> seats) {
        return seats.stream()
                .mapToDouble(seat -> pricingStrategy.calculatePrice(seat.getSeatType(),screening))
                .sum();
    }

    private void generateTickets(Booking booking) {
        for (Seat seat : booking.getSeats()) {
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setPrice(pricingStrategy.calculatePrice(seat.getSeatType(),booking.getScreening()));
            booking.getTickets().add(ticket);
        }
    }

    private void lockSeats(Screening screening, List<Seat> seats) {

        String screeningId = screening.getScreeningId();

        lockedSeatsMap.putIfAbsent(screeningId, ConcurrentHashMap.newKeySet());
        Set<String> lockedSeats = lockedSeatsMap.get(screeningId);

        // Ensure atomic lock
        synchronized (lockedSeats) {
            for (Seat seat : seats) {
                if (lockedSeats.contains(seat.getSeatId())) {
                    throw new RuntimeException(
                            "Seat already locked: " + seat.getSeatId()
                    );
                }
            }

            for (Seat seat : seats) {
                lockedSeats.add(seat.getSeatId());
            }
        }
    }

    private void unlockSeats(Screening screening, List<Seat> seats) {

        String screeningId = screening.getScreeningId();
        Set<String> lockedSeats = lockedSeatsMap.get(screeningId);

        if (lockedSeats == null) {
            return;
        }

        synchronized (lockedSeats) {
            for (Seat seat : seats) {
                lockedSeats.remove(seat.getSeatId());
            }

            // cleanup if empty
            if (lockedSeats.isEmpty()) {
                lockedSeatsMap.remove(screeningId);
            }
        }
    }
    
    
}
