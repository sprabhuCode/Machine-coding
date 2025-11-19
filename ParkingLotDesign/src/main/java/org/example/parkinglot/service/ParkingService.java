package org.example.parkinglot.service;

import org.example.parkinglot.entites.Ticket;
import org.example.parkinglot.entites.Vehicle;
import org.example.parkinglot.entites.VehicleSize;
import org.example.parkinglot.entites.slot.ParkingSlot;
import org.example.parkinglot.entites.slot.Slot;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ParkingService {

    Map<VehicleSize, List<Slot>> unoccupiedSlot;
    Map<Vehicle, Slot> vehicleSlotMap;
    Map<String, Ticket> activeTicket;
    private int ticket;


    ParkingService(){
        this.unoccupiedSlot = createDefaultSlots();
        this.vehicleSlotMap = new HashMap<>();
        this.activeTicket = new HashMap<>();
        this.ticket=0;
    }

    private Slot fetchSlotForVehicle(Vehicle vehicle){
        List<Slot> slots = unoccupiedSlot.get(vehicle.getSize());
        if(slots.isEmpty()){
            System.out.print("No available slot for vehicle of size="+ vehicle.getSize());
            return null;
        }
        return slots.remove(0);
    }

    public Ticket parkVehicle(Vehicle vehicle){
        Slot slot = fetchSlotForVehicle(vehicle);
        if(Objects.nonNull(slot)) {
            slot.occupy(vehicle);
            vehicleSlotMap.put(vehicle, slot);
            Ticket ticket = new Ticket(this.ticket++, vehicle, slot, Instant.now(), 0);
            activeTicket.put(vehicle.getLicenseNumber(), ticket);
            System.out.print("Ticket for vehicle : " + ticket);
            return ticket;
        }
        System.out.println("Vehicle not parked " + vehicle);
        return null;
    }

    public Ticket unparkVehicle(String licensePlate){
        Ticket ticket = activeTicket.remove(licensePlate);
       Slot slot = vehicleSlotMap.remove(ticket.getVehicle());
        if(Objects.isNull(slot)){
            System.out.println("Vehicle not found in parking lot " + ticket.getVehicle());
            return null;
        }
        slot.vacate();
        unoccupiedSlot.get(ticket.getVehicle().getSize()).add(slot);
        ticket.setExitTime(Instant.now());
        System.out.println("Vehicle unparked successfully " + ticket.getVehicle());
        return ticket;
    }

    private static Map<VehicleSize, List<Slot>> createDefaultSlots() {
        Map<VehicleSize, List<Slot>> map = new EnumMap<>(VehicleSize.class);

        int slotCounter = 1;

        for (VehicleSize size : VehicleSize.values()) {
            List<Slot> slots = new ArrayList<>();

            for (int i = 0; i < 3; i++) {  // 3 slots per size
                slots.add(new ParkingSlot(slotCounter++, null));
            }

            map.put(size, slots);
        }

        return map;
    }

}
