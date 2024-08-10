package com.ParkingLotDesign.ParkingLotDesign.service;

import com.ParkingLotDesign.ParkingLotDesign.ParkingLotStorage.ParkingLotStorage;
import com.ParkingLotDesign.ParkingLotDesign.dto.ParkingResponseDTO;
import com.ParkingLotDesign.ParkingLotDesign.enums.VehicleType;
import com.ParkingLotDesign.ParkingLotDesign.exception.UnknownVehicleType;
import com.ParkingLotDesign.ParkingLotDesign.model.*;
import com.ParkingLotDesign.ParkingLotDesign.strategy.BikeFirstStrategy;
import com.ParkingLotDesign.ParkingLotDesign.strategy.CarFirstStrategy;
import com.ParkingLotDesign.ParkingLotDesign.strategy.ParkingStrategy;
import com.ParkingLotDesign.ParkingLotDesign.strategy.TruckFirstStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingLotStorage storage = ParkingLotStorage.getInstance();

    public ParkingResponseDTO parkVehicle(Vehicle vehicle){
        ParkingStrategy strategy = getParkingStrategy(vehicle.getType());
        ParkingLot parkingLot = storage.getParkingLot();


        for(Floor floor: parkingLot.getFloors()){
            Slot slot = strategy.selectSlot(floor, vehicle);
            if(slot != null){
                slot.setOccupied(true);
                slot.setVehicle(vehicle);

                Ticket ticket = new Ticket();
//                ticket.setId(parkingLot.getId()+ "_" + floor.getNumber()+"_" + slot.getNumber());
//                ticket.setVehicle(vehicle);
//                ticket.setSlot(slot);
//                ticket.setFloor(floor);
//                ticket.setParkingLot(parkingLot);
//                ticket.setIssueTime(LocalDateTime.now());

                storage.getTickets().put(ticket.getId(), ticket);
                return new ParkingResponseDTO(
                        ticket.getId(),
                        vehicle,
                        slot.getNumber(),
                        floor.getNumber(),
                        parkingLot.getId(),
                        ticket.getIssueTime(),
                        true,
                        null

                );
            }
        }
        return new ParkingResponseDTO(
                null,
                null,
                0,
                0,
                null,
                null,
                false,
                "No available slot for type :" +  vehicle.getType()

        );
    }

    private ParkingStrategy getParkingStrategy(VehicleType vehicleType){
        switch (vehicleType){
            case TRUCK:
                return new TruckFirstStrategy();
            case BIKE:
                return new BikeFirstStrategy();
            case CAR:
                return new CarFirstStrategy();
            default:
                 throw new UnknownVehicleType("Unknown vehicle type: " + vehicleType);
        }
    }

    public Vehicle unparkVehicle(String ticketId){
        Ticket ticket = storage.getTickets().remove(ticketId);
        if(ticket == null){
            throw new RuntimeException("Invalid ticket id: " + ticketId);
        }
        Slot slot = ticket.getSlot();
        slot.setOccupied(false);
        Vehicle vehicle = slot.getVehicle();
        slot.setVehicle(null);
        return vehicle;
    }

    public Map<Integer, Long> getFreeSlotsPerFloor(VehicleType type){
        ParkingLot parkingLot = storage.getParkingLot();
        Map<Integer, Long> freeSlots = new HashMap<>();
        for(Floor floor: parkingLot.getFloors()){
            long count = floor
                    .getSlots()
                    .stream()
                    .filter(slot -> !slot.isOccupied() && slot.getType() == type)
                    .count();
            freeSlots.put(floor.getNumber(), count);
        }
        return freeSlots;
    }

    public Map<Integer, List<Integer>> getAllFreeSlotsPerFloor(VehicleType type){
        ParkingLot parkingLot = storage.getParkingLot();
        Map<Integer, List<Integer>> freeSlots = new HashMap<>();
        for(Floor floor: parkingLot.getFloors()){
            List<Integer> slots = floor.
                    getSlots().
                    stream()
                    .filter(slot -> !slot.isOccupied() && slot.getType() == type)
                    .map(Slot::getNumber)
                    .collect(Collectors.toList());
            freeSlots.put(floor.getNumber(), slots);
        }
        return freeSlots;
    }

    public Map<Integer, List<Integer>> getAllOccupiedSlotsPerFloor(VehicleType type){
        ParkingLot parkingLot = storage.getParkingLot();
        Map<Integer, List<Integer>> occupiedSlots = new HashMap<>();
        for(Floor floor: parkingLot.getFloors()){
            List<Integer> slots = floor.
                    getSlots()
                    .stream()
                    .filter(slot -> slot.isOccupied() && slot.getType() == type)
                    .map(Slot::getNumber)
                    .collect(Collectors.toList());
            occupiedSlots.put(floor.getNumber(), slots);
        }
        return occupiedSlots;
    }
}
