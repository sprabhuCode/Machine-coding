package org.example.services.strategy;

import org.example.entities.Screening;
import org.example.entities.SeatType;

public interface PricingStrategy {
    double calculatePrice(SeatType seatType, Screening screening);
}
