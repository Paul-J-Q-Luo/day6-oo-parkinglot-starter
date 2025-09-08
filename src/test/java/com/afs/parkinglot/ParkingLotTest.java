package com.afs.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_park_given_parking_lot_with_available_position_and_a_car() {
        Car car = new Car("123456");
        ParkingLot parkingLot = new ParkingLot();

        Ticket ticket = parkingLot.park(car);

        Assertions.assertNotNull(ticket);
    }
}
