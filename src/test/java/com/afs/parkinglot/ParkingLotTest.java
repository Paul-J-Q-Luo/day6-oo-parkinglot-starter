package com.afs.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParkingLotTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

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
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

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

    @Test
    public void should_return_null_when_park_given_parking_lot_reach_max_capacity_with_parked_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("123456");
        Car car2 = new Car("234567");

        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        Assertions.assertNotNull(ticket1);
        Assertions.assertNull(ticket2);
    }

    /*Given
    一个顾客持有一个无效的停车票（未被识别或已使用过）。
    When
    顾客尝试使用该票取车。
    Then
    系统应提示错误信息：“Unrecognized parking ticket.”，且不允许取车。*/
    @Test
    public void should_print_unrecognized_parking_ticket_when_fetch_given_unrecognized_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123456");
        Ticket invalidTicket = new Ticket(1, car, parkingLot);

        Car fetchedCar = parkingLot.fetch(invalidTicket);

        Assertions.assertNull(fetchedCar);
        Assertions.assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }

    @Test
    public void should_print_unrecognized_parking_ticket_when_fetch_given_used_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("123456");
        parkingLot.park(car);
        Ticket invalidTicket = new Ticket(1, car, parkingLot);

        Car fetchedCar1 = parkingLot.fetch(invalidTicket);
        Car fetchedCar2 = parkingLot.fetch(invalidTicket);

        Assertions.assertNotNull(fetchedCar1);
        Assertions.assertNull(fetchedCar2);
        Assertions.assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }

    /*Given
    一个停车场已满，没有空位。
    When
    顾客尝试将车停入该停车场。
    Then
    系统应提示错误信息：“No available position.”，且不允许停车。*/
    @Test
    public void should_print_no_available_position_when_park_given_no_available_position() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("123456");
        Car car2 = new Car("234567");

        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        Assertions.assertNotNull(ticket1);
        Assertions.assertNull(ticket2);
        Assertions.assertTrue(outputStream.toString().contains("No available position."));
    }
}
