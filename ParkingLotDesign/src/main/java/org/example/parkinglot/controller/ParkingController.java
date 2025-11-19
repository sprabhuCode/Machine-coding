package org.example.parkinglot.controller;

import org.example.parkinglot.entites.Ticket;
import org.example.parkinglot.entites.Vehicle;
import org.example.parkinglot.entites.response.VehicleRequest;
import org.example.parkinglot.entites.response.VehicleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.parkinglot.service.ParkingLotService;

import java.time.Duration;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingLotService parkingLotService;

    public ParkingController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping("/enter")
    public ResponseEntity<VehicleResponse> enterVehicle(
            @RequestBody VehicleRequest vehicleRequest) {

        Vehicle vehicle = new Vehicle(vehicleRequest.getLicenseNumber(), vehicleRequest.getSize());
        Ticket ticket = parkingLotService.enterVehicle(vehicle);

        VehicleResponse response = new VehicleResponse();
        response.setTicketId(ticket.getTicketId());
        response.setLicenseNumber(ticket.getVehicle().getLicenseNumber());
        response.setSize(ticket.getVehicle().getSize());
        response.setSpotNumber(ticket.getVehicleSlot().getSpotNumber());
        response.setEntryTime(ticket.getEntryTime());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/exit/{licenseNumber}")
    public ResponseEntity<VehicleResponse> exitVehicle(
            @PathVariable String licenseNumber) {

        Ticket ticket = parkingLotService.exitVehicle(licenseNumber);

        VehicleResponse response = new VehicleResponse();
        response.setTicketId(ticket.getTicketId());
        response.setLicenseNumber(ticket.getVehicle().getLicenseNumber());
        response.setFare(ticket.getFare());

        Duration duration = ticket.getTotalTime();
        response.setDurationMinutes(duration.toMinutes());

        return ResponseEntity.ok(response);
    }

}
