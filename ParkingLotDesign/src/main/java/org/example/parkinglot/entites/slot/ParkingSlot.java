package org.example.parkinglot.entites.slot;

import lombok.AllArgsConstructor;
import org.example.parkinglot.entites.Vehicle;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ParkingSlot implements Slot{

    private int slotId;
    private Vehicle vehicle;

    @Override
    public boolean isAvailable() {
        return !Objects.isNull(vehicle);
    }

    @Override
    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void vacate() {
        this.vehicle = null;
    }

    @Override
    public int getSpotNumber() {
        return slotId;
    }
}
