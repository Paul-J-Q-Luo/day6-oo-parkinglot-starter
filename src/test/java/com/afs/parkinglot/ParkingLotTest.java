package com.afs.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_park_given_parking_lot_with_available_position_and_a_car() {
        Car car = new Car("123456");
        ParkingLot parkingLot = new ParkingLot();
        Ticket expectTicket = new Ticket(1, car, parkingLot);

        Ticket ticket = parkingLot.park(car);

        Assertions.assertEquals(expectTicket, ticket);
    }

    @Test
    public void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123456");
        Ticket ticket = new Ticket(1, car, parkingLot);
        parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(ticket);

        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_right_car_when_fetch_given_parking_lot_with_two_parked_car_and_each_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car("123456");
        Car car2 = new Car("234567");
        Ticket ticket1 = new Ticket(1, car1, parkingLot);
        parkingLot.park(car1);
        Ticket ticket2 = new Ticket(2, car2, parkingLot);
        parkingLot.park(car2);

        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);

        Assertions.assertEquals(car1, fetchedCar1);
        Assertions.assertEquals(car2, fetchedCar2);
    }

    @Test
    public void should_return_null_when_fetch_given_parking_lot_with_parked_car_and_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car("123456");
        Car car2 = new Car("234567");
        Ticket ticket = new Ticket(2, car2, parkingLot);
        parkingLot.park(car1);

        Car fetchedCar = parkingLot.fetch(ticket);

        Assertions.assertNull(fetchedCar);
    }

    @Test
    public void should_return_null_when_fetch_given_parking_lot_with_parked_car_and_ticket_is_used() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123456");
        parkingLot.park(car);
        Ticket ticket = new Ticket(1, car, parkingLot);

        Car fetchedCar = parkingLot.fetch(ticket);
        Car fetchedCar1 = parkingLot.fetch(ticket);

        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNull(fetchedCar1);
    }
//
//    @Test
//    public void should_return_null_when_park_given_parking_lot_reach_max_capacity_with_parked_car() {
//        Car car = new Car("123456");
//        ParkingLot parkingLot = new ParkingLot();
//
//        Ticket ticket = parkingLot.park(car);
//
//        Assertions.assertNull(ticket);
//    }
}
