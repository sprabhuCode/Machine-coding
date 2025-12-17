package org.example.services.strategy;

import org.example.entities.Screening;
import org.example.entities.SeatType;
import org.springframework.stereotype.Service;

@Service
public class DefaultPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(SeatType seatType, Screening screening) {
        double basePrice = screening.getBasePrice();
        return switch (seatType) {
            case REGULAR -> basePrice;
            case PREMIUM -> basePrice * 1.5;
            case VIP -> basePrice * 2;
            default -> throw new IllegalArgumentException("Unknown seat type: " + seatType);
        };
    }
}
