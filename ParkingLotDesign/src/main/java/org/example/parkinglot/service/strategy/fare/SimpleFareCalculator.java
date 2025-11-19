package org.example.parkinglot.service.strategy.fare;

import org.example.parkinglot.entites.Ticket;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class SimpleFareCalculator implements IFareCalculatorStrategy{
    @Override
    public double calculateFare(Ticket ticket) {
        Duration duration = ticket.getTotalTime();
        long hours = Math.max(1, (duration.toMinutes() + 59) / 60);

        double ratePerHour;
        switch (ticket.getVehicle().getSize()) {
            case SMALL -> ratePerHour = 20.0;
            case MEDIUM -> ratePerHour = 30.0;
            case LARGE -> ratePerHour = 40.0;
            default -> ratePerHour = 30.0;
        }

        return hours * ratePerHour;
    }
}
