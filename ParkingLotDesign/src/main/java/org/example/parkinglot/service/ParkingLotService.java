package org.example.parkinglot.service;

import org.example.parkinglot.entites.Ticket;
import org.example.parkinglot.entites.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    ParkingService parkingService;
    FareCalulatorService fareCalulatorService;

    public ParkingLotService(ParkingService parkingService, FareCalulatorService fareCalulatorService){
        this.parkingService = parkingService;
        this.fareCalulatorService = fareCalulatorService;
    }

    public Ticket enterVehicle(Vehicle vehicle){
        return parkingService.parkVehicle(vehicle);
    }

    public Ticket exitVehicle(String licenseNumber){
        Ticket ticket = parkingService.unparkVehicle(licenseNumber);
        if(ticket == null) {
            System.out.println("Vehicle not found in parking lot: " + licenseNumber);
            return null;
        }
        ticket.setFare(fareCalulatorService.calculateFare(ticket));
        return ticket;
    }
}
