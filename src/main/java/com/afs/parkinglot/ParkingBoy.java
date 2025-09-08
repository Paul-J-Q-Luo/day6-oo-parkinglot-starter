package com.afs.parkinglot;

import java.util.List;
import java.util.Objects;

public record ParkingBoy(List<ParkingLot> parkingLots) {

    public Ticket park(Car car) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.park(car))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("No available parking lot.");
                    return null;
                });
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null || ticket.getParkingLot() == null) {
            System.out.println("Unrecognized parking ticket.");
            return null;
        }
        return ticket.getParkingLot().fetch(ticket);
    }
}
