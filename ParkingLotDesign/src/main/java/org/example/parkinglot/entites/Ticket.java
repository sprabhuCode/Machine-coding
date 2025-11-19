package org.example.parkinglot.entites;

import org.example.parkinglot.entites.slot.Slot;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Data
public class Ticket {
    int ticketId;
    Instant entryTime;
    Instant exitTime;
    Vehicle vehicle;
    Slot vehicleSlot;
    double fare;

    public Ticket(int ticketId, Vehicle vehicle, Slot slot, Instant entryTime, double fare){
      this.ticketId =ticketId;
      this.vehicle =vehicle;
      this.vehicleSlot = slot;
      this.entryTime = entryTime;
      this.fare = fare;
    }

    public Duration getTotalTime(){
        return Duration.between(exitTime, entryTime);
    }

}
