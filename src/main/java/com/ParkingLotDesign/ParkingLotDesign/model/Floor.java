package com.ParkingLotDesign.ParkingLotDesign.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Floor {
    private int number;
    private List<Slot> slots;

}
