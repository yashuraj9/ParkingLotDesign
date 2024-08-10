package com.ParkingLotDesign.ParkingLotDesign.model;


import com.ParkingLotDesign.ParkingLotDesign.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
    private int number;
    private VehicleType type;
    private boolean isOccupied;
    private Vehicle vehicle;

}
