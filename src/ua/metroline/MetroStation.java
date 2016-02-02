package ua.metroline;

import ua.depo.DepoWagon;
import ua.passengers.Passenger;

import java.util.LinkedList;

/**
 * Created by liny on 24.12.15.
 */
public class MetroStation {
    private String name;
    private LinkedList<Passenger> passengers;
    private LinkedList<Passenger> passengersOnVestibule;
    LinkedList<Escalator> escalators;

    public MetroStation(String name) {
        this.name = name;
        passengers = new LinkedList<>();
    }

    //synchronized pass with station  go to wagon
    public synchronized void PassOnWagon (DepoWagon depoWagon){
        //if 0 pass proverit
        System.out.println("Passengers size on station --------------"+passengers.size());
        if (!passengers.isEmpty()) {
            depoWagon.getPassengers().add(passengers.poll());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


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

    public LinkedList<Escalator> getEscalators() {
        return escalators;
    }

    public void setEscalators(LinkedList<Escalator> escalators) {
        this.escalators = escalators;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
