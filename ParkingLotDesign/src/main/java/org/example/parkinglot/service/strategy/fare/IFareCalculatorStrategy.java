package org.example.parkinglot.service.strategy.fare;

import org.example.parkinglot.entites.Ticket;

public interface IFareCalculatorStrategy {
    double calculateFare(Ticket ticket);
}
