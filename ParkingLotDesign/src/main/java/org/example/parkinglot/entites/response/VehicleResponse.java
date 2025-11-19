package org.example.parkinglot.entites.response;

import org.example.parkinglot.entites.VehicleSize;
import lombok.Data;

import java.time.Instant;

@Data
public class VehicleResponse {
    private int ticketId;
    private String licenseNumber;
    private double fare;
    private long durationMinutes;
    private VehicleSize size;
    private int spotNumber;
    private Instant entryTime;
}
