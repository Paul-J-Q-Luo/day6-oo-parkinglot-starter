package com.afs.parkinglot;

import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            Ticket ticket = parkingLot.park(car);
            if (ticket != null) {
                return ticket;
            }
        }
        System.out.println("No available parking lot.");
        return null;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null || ticket.getParkingLot() == null) {
            System.out.println("Unrecognized parking ticket.");
            return null;
        }
        return ticket.getParkingLot().fetch(ticket);
    }

}
