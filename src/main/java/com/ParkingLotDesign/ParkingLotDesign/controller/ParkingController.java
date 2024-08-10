package com.ParkingLotDesign.ParkingLotDesign.controller;


import com.ParkingLotDesign.ParkingLotDesign.dto.ParkingResponseDTO;
import com.ParkingLotDesign.ParkingLotDesign.enums.VehicleType;
import com.ParkingLotDesign.ParkingLotDesign.model.Ticket;
import com.ParkingLotDesign.ParkingLotDesign.model.Vehicle;
import com.ParkingLotDesign.ParkingLotDesign.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<ParkingResponseDTO> parkVehicle(@RequestBody Vehicle vehicle){
        ParkingResponseDTO response = parkingService.parkVehicle(vehicle);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/unpark/{ticketId}")
    public ResponseEntity<Vehicle> unparkVehicle(@PathVariable String ticketId){
        Vehicle vehicle = parkingService.unparkVehicle(ticketId);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/free-slots/{type}")
    public ResponseEntity<Map<Integer, Long>> getFreeSlotsPerFloor(@PathVariable VehicleType type) {
        Map<Integer,Long> freeSlots = parkingService.getFreeSlotsPerFloor(type);
        return ResponseEntity.ok(freeSlots);
    }

    @GetMapping("/all-free-slots/{type}")
    public ResponseEntity<Map<Integer, List<Integer>>> getAllFreeSlotsPerFloor(@PathVariable VehicleType type){
        Map<Integer, List<Integer>> freeSlots = parkingService.getAllFreeSlotsPerFloor(type);
        return ResponseEntity.ok(freeSlots);
    }

    @GetMapping("/all-occupied-slots/{type}")
    public ResponseEntity<Map<Integer, List<Integer>>> getAllOccupiedSlotsPerFloor(@PathVariable VehicleType type){
        Map<Integer, List<Integer>> occupiedSlots = parkingService.getAllOccupiedSlotsPerFloor(type);
        return ResponseEntity.ok(occupiedSlots);
    }
}
