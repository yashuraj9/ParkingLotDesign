package com.ParkingLotDesign.ParkingLotDesign.strategy;

import com.ParkingLotDesign.ParkingLotDesign.enums.VehicleType;
import com.ParkingLotDesign.ParkingLotDesign.model.Floor;
import com.ParkingLotDesign.ParkingLotDesign.model.Slot;
import com.ParkingLotDesign.ParkingLotDesign.model.Vehicle;

public class BikeFirstStrategy implements ParkingStrategy{
    @Override
    public Slot selectSlot(Floor floor, Vehicle vehicle){
        for (Slot slot: floor.getSlots()){
            if(!slot.isOccupied() && slot.getType() == VehicleType.BIKE){
                return slot;
            }
        }
        return null;
    }
}
