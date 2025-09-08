package com.afs.parkinglot;

public record Ticket(int position, Car car, ParkingLot parkingLot) {
    public ParkingLot getParkingLot() {
        return this.parkingLot;
    }
}
