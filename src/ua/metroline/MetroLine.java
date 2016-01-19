package ua.metroline;

import ua.depo.Train;
import java.util.LinkedList;

/**
 * Created by liny on 24.12.15.
 */
public class MetroLine {
    private String name;
    private LinkedList<Train> trainsOnLine;
    private LinkedList<MetroStation> metroStations;

    @Override
    public String toString() {
        return "\nMetro Line - " + name +
                " \nMetro Stations " + metroStations +
                " \nTrains On Line= " + trainsOnLine.size()  ;
    }

    public MetroLine(String name, LinkedList<Train> trainsOnLine, LinkedList<MetroStation> metroStations) {
        this.name = name;
        this.trainsOnLine = trainsOnLine;
        this.metroStations = metroStations;
    }

    public LinkedList<MetroStation> getMetroStations() {
        return metroStations;
    }

    public void setMetroStations(LinkedList<MetroStation> metroStations) {
        this.metroStations = metroStations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Train> getTrainsOnLine() {
        return trainsOnLine;
    }

    public void setTrainsOnLine(LinkedList<Train> trainsOnLine) {
        this.trainsOnLine = trainsOnLine;
    }
}