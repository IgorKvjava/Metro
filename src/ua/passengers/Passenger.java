package ua.passengers;

/**
 * Created by liny on 24.12.15.
 */
public class Passenger {
    String name;
    public Passenger(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
