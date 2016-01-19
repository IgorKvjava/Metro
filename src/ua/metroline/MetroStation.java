package ua.metroline;

import ua.passengers.Passenger;

import java.util.LinkedList;

/**
 * Created by liny on 24.12.15.
 */
public class MetroStation {
    private String name;
    private LinkedList<Passenger> passengers;
    private LinkedList<Passenger> passengersOnVestibule;
    Escalator escalator;

    public MetroStation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name ;
    }

    public LinkedList<Passenger> getPassengersOnVestibule() {
        return passengersOnVestibule;
    }

    public void setPassengersOnVestibule(LinkedList<Passenger> passengersOnVestibule) {
        this.passengersOnVestibule = passengersOnVestibule;
    }

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(LinkedList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Escalator getEscalator() {
        return escalator;
    }

    public void setEscalator(Escalator escalator) {
        this.escalator = escalator;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
