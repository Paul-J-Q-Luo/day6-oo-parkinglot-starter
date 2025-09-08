package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int maxCapacity = 10;
    private List<Car> parkedCars = new ArrayList<>();

    public Ticket park(Car car) {
        if (parkedCars.size() < maxCapacity) {
            parkedCars.add(car);
            return new Ticket();
        }
        return null;
    }

    public Car fetch(Ticket ticket) {
        return null;
    }
}
