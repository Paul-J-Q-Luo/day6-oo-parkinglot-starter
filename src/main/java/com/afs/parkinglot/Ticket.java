package com.afs.parkinglot;

public class Ticket {
    private int ticketId;
    private boolean used;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
