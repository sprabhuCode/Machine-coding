package org.example.parkinglot.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Vehicle {
    String licenseNumber;
    VehicleSize size;

}
