package com.ParkingLotDesign.ParkingLotDesign.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {

    private String id;
    private List<Floor> floors;

}
