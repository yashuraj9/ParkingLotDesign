package com.ParkingLotDesign.ParkingLotDesign.controller;

import com.ParkingLotDesign.ParkingLotDesign.model.Floor;
import com.ParkingLotDesign.ParkingLotDesign.model.ParkingLot;
import com.ParkingLotDesign.ParkingLotDesign.model.Slot;
import com.ParkingLotDesign.ParkingLotDesign.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lot")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/create")
    public ResponseEntity<ParkingLot> createParkingLot(){
        ParkingLot parkingLot = parkingLotService.createParkingLot();
        return ResponseEntity.ok(parkingLot);
    }

    @PostMapping("/add-floor/{floorNumber}")
    public ResponseEntity<Floor> addFloor(@PathVariable int floorNumber){
        Floor floor = parkingLotService.addFloor(floorNumber);
        return ResponseEntity.ok(floor);
    }

    @PostMapping("/add-slot/{floorNumber}")
    public ResponseEntity<Slot> addSlot(@PathVariable int floorNumber, @RequestBody Slot slot) {
        Slot addedSlot = parkingLotService.addSlot(floorNumber, slot);
        return ResponseEntity.ok(addedSlot);
    }
}
