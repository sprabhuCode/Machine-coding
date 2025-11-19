package org.example.parkinglot.entites.response;

import org.example.parkinglot.entites.VehicleSize;
import lombok.Data;

@Data
public class VehicleRequest {
    private String licenseNumber;
    private VehicleSize size;

}
