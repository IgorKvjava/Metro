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
        passengers=new LinkedList<>();
        this.name = name;
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
    public void sinhronizedThreade(LinkedList<MetroLine> metroLines, Escalator  escalator ) {
        synchronized (metroLines.get(0).getMetroStations().get(0).getPassengersOnVestibule()) {
            if (!metroLines.get(0).getMetroStations().get(0).getPassengersOnVestibule().isEmpty()) {
                Passenger pas = metroLines.get(0).getMetroStations().get(0).getPassengersOnVestibule().poll();
                escalator.getPassengers().add(pas);
                //sleep
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                escalators.get(i).setPassengers(passengersOnEscalator);
                metroLines.get(0).getMetroStations().get(0).getPassengers().add( escalator.getPassengers().poll());
                //escalators.get(i).getPassengers().clear();
                System.out.println("on escalator ::: " + escalator.name + " passengers "
                        +  pas);

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
