package org.example.services;

import org.example.entities.Layout;
import org.example.entities.Movie;
import org.example.entities.Screening;
import org.example.entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ScreeningService {

    @Autowired
            BookingService bookingService;
    Map<String, List<Screening>> movieScreeningMap;

    public List<Seat> getAvailableSeats(Screening screening) {
        Layout layout = screening.getRoom().getLayout();
        Set<String> lockedSeats =
                bookingService.getLockedSeatsForScreening(screening.getScreeningId());
        return layout.getAllSeats().stream().filter(seat -> !lockedSeats.contains(seat.getSeatId())).toList();
    }

    public List<Screening> listScreenings() {
        return movieScreeningMap.values().stream().flatMap(List::stream).toList();
    }

    public boolean areSeatsAvailable(Screening screening, List<Seat> requestedSeats) {
        List<Seat> allSeats = screening.getRoom().getLayout().getAllSeats();
        Set<String> lockedSeats =
                bookingService.getLockedSeatsForScreening(screening.getScreeningId());

        for (Seat seat : requestedSeats) {
            if (!allSeats.contains(seat) ||
                    lockedSeats.contains(seat.getSeatId())) {
                return false;
            }
        }
        return true;
    }

    public List<Screening> fetchScreeningDetails(Movie movie) {
        return movieScreeningMap.get(movie.getTitle());
    }


}
