package com.ParkingLotDesign.ParkingLotDesign.ParkingLotStorage;

import com.ParkingLotDesign.ParkingLotDesign.model.Floor;
import com.ParkingLotDesign.ParkingLotDesign.model.ParkingLot;
import com.ParkingLotDesign.ParkingLotDesign.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotStorage {

    private static ParkingLotStorage instance;
    private ParkingLot parkingLot;
    private Map<String, Ticket> tickets;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public ParkingLotStorage() {
        this.parkingLot = new ParkingLot();
        this.parkingLot.setId("PR1234");
        this.parkingLot.setFloors(new ArrayList<>());
        this.tickets = new HashMap<>();
    }

    public static synchronized ParkingLotStorage getInstance(){
        if(instance == null){
            instance = new ParkingLotStorage();
        }
        return instance;
    }

}
