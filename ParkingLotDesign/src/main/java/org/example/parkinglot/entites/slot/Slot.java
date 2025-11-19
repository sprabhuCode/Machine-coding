package org.example.parkinglot.entites.slot;

import org.example.parkinglot.entites.Vehicle;

public interface Slot {

    boolean isAvailable();

    void occupy(Vehicle vehicle);

    void vacate();

    int getSpotNumber();
}
