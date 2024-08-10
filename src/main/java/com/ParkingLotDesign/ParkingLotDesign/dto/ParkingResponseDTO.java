package com.ParkingLotDesign.ParkingLotDesign.dto;


import com.ParkingLotDesign.ParkingLotDesign.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingResponseDTO {
    private String id;
    private Vehicle vehicle;
    private int slotNumber;
    private int floorNumber;
    private String parkingLotId;
    private LocalDateTime issueTime;
    private boolean success;
    private String errorCode;

}
