package com.ParkingLotDesign.ParkingLotDesign.service;

import com.ParkingLotDesign.ParkingLotDesign.ParkingLotStorage.ParkingLotStorage;
import com.ParkingLotDesign.ParkingLotDesign.exception.FloorNotFoundException;
import com.ParkingLotDesign.ParkingLotDesign.model.Floor;
import com.ParkingLotDesign.ParkingLotDesign.model.ParkingLot;
import com.ParkingLotDesign.ParkingLotDesign.model.Slot;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParkingLotService {
    private final ParkingLotStorage storage = ParkingLotStorage.getInstance();

    public ParkingLot createParkingLot(){
        return storage.getParkingLot();
    }

    public Floor addFloor(int floorNumber){
        ParkingLot parkingLot = storage.getParkingLot();
        Floor floor = new Floor();
        floor.setNumber(floorNumber);
        floor.setSlots(new ArrayList<>());
        parkingLot.getFloors().add(floor);
        return floor;
    }

    public Slot addSlot(int floorNumber, Slot slot){
        ParkingLot parkingLot = storage.getParkingLot();
        Floor floor = parkingLot
                .getFloors()
                .stream()
                .filter(f -> f.getNumber() == floorNumber)
                .findFirst()
                .orElseThrow(() -> new FloorNotFoundException("Floor not found: " + floorNumber));
        floor.getSlots().add(slot);
        return slot;
    }


}
