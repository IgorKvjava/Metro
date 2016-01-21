package ua.metroline;

import ua.passengers.Passenger;
import java.util.LinkedList;

/**
 * Created by univer3 on 1/17/2016.
 */
public class Escalator {
    String name;
    private LinkedList<Passenger> passengers;


    public Escalator(String name) {
        this.name = name;
    }

    public Escalator(String name, LinkedList<Passenger> passengers) {
        this.name = name;
        this.passengers = passengers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers (LinkedList < Passenger > passengers) {


        this.passengers = passengers;

    }

    //sinhronized thread Passengers on Escalator
    public void sinhronizedThreade(LinkedList<MetroLine> metroLines,LinkedList<Passenger> passengersOnEscalator,
                                  LinkedList<Escalator> escalators,int i) {
        synchronized (metroLines.get(0).getMetroStations().get(0).getPassengersOnVestibule()) {
            if (!metroLines.get(0).getMetroStations().get(0).getPassengersOnVestibule().isEmpty()) {
                passengersOnEscalator.add(metroLines.get(0).getMetroStations().get(0).getPassengersOnVestibule().poll());
                escalators.get(i).setPassengers(passengersOnEscalator);
                System.out.println("passengers on escalator ::: " + i + " " + passengersOnEscalator.getLast());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
