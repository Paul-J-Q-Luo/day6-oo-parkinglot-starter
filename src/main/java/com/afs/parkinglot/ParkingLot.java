package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ParkingLot {
    private int capacity = 10;
    private static final int MAXCAPACITY = 10;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot() {
        this.capacity = MAXCAPACITY;
    }

    public Ticket park(Car car) {
        return IntStream.rangeClosed(1, capacity).boxed()
                .filter(position -> ticketCarMap.keySet().stream().noneMatch(ticket -> ticket.position() == position))
                .findFirst()
                .map(position -> {
                    Ticket ticket = new Ticket(position, car, this);
                    ticketCarMap.put(ticket, car);
                    return ticket;
                })
                .orElse(null);
    }

    public Car fetch(Ticket ticket) {
        return ticketCarMap.remove(ticket);
    }
}
