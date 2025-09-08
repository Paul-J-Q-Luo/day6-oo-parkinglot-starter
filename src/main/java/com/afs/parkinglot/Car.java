package com.afs.parkinglot;

import java.util.Objects;

public class Car {
    private String number;

    public Car(String number) {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
