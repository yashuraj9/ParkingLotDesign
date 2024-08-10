package com.ParkingLotDesign.ParkingLotDesign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String id;
    private Vehicle vehicle;
    private Slot slot;
    private Floor floor;
    private ParkingLot parkingLot;
    private LocalDateTime issueTime;
}
