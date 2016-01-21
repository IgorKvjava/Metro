package ua.start;

import java.io.IOException;

/**
 * Created by univer3 on 12/12/2015.
 */
public class Start {
    public static void main(String[] args) throws InterruptedException {
        Builder builder=new Builder();
        //Completion Trains-------------------------------------------------------------
        builder.builderTrains();
        System.out.println("depo "+builder.trains.size()+" trains");
        //Completion metro driver-------------------------------------------------
        builder.builderMetroDrivers();
        System.out.println(" Drivers count-"+builder.metroDriverPriorityQueue.size());
        //Completion Metro Line
        System.out.println("metroDriverPriorityQueue "+builder.metroDriverPriorityQueue);
        builder.builderMetroLine(7,builder.trains,builder.metroDriverPriorityQueue);
        System.out.println(" Metro Line-"+builder.metroLines);
        System.out.println("metroDriverPriorityQueue "+builder.metroDriverPriorityQueue+"\n" +
                "----------------------------------------------------------------------------------");


       builder.passengerGoVestibule();
        builder.passengerGoEscalator(0);
        builder.passengerGoEscalator(1);
        builder.passengerGoEscalator(2);
        builder.trainOnStation();






    }
}
