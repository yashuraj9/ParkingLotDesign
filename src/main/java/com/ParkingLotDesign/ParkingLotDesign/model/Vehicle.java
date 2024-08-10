package com.ParkingLotDesign.ParkingLotDesign.model;

import com.ParkingLotDesign.ParkingLotDesign.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private String registrationNumber;
    private String color;
    private VehicleType type;


}
