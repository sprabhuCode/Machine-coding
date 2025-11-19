package org.example.parkinglot.service;

import org.example.parkinglot.entites.Ticket;
import org.example.parkinglot.service.strategy.fare.IFareCalculatorStrategy;
import org.springframework.stereotype.Service;

@Service
public class FareCalulatorService {

    private final IFareCalculatorStrategy strategy;

    public FareCalulatorService(IFareCalculatorStrategy strategy) {
        this.strategy = strategy;
    }
    public double calculateFare(Ticket ticket) {
        return strategy.calculateFare(ticket);
    }
}
