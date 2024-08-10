package com.ParkingLotDesign.ParkingLotDesign.strategy;

import com.ParkingLotDesign.ParkingLotDesign.model.Floor;
import com.ParkingLotDesign.ParkingLotDesign.model.Slot;
import com.ParkingLotDesign.ParkingLotDesign.model.Vehicle;

public interface ParkingStrategy {
    Slot selectSlot(Floor floor, Vehicle vehicle);
}
