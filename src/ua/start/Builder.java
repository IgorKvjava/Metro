package ua.start;

import ua.depo.DepoWagon;
import ua.depo.Train;
import ua.driver.MetroDriver;
import ua.metroline.Escalator;
import ua.metroline.MetroLine;
import ua.metroline.MetroStation;
import ua.passengers.Passenger;

import java.util.*;

/**
 * Created by liny on 24.12.15.
 */
public class Builder {
    // List
    LinkedList<DepoWagon> wagons = new LinkedList<>();
    LinkedList<Train> trains = new LinkedList<>();
    PriorityQueue<MetroDriver> metroDriverPriorityQueue;
    LinkedList<MetroLine> metroLines=new LinkedList<>();
    LinkedList<Passenger> passengersOnVestibule=new LinkedList<>();
    LinkedList<Passenger> passengersOnEscalator=new LinkedList<>();
    LinkedList<Passenger> passengersOnTrain=new LinkedList<>();
    LinkedList<Escalator> escalators=new LinkedList<>();


    String [] nameStations={"Red","Green","Blue"};
    //Completion Trains-------------------------------------------------------------------------------------------
    public void builderTrains() {
        //Consist wagons-------------------------------------------------------------
        String name = "wagon-";
        int count = 0;
        boolean mainWagon=true;
        for (int i=0;i<104;i++ ){
            wagons.add(new DepoWagon(name+i,mainWagon));
            if((Math.random()*100)>50)
            {mainWagon=false;}
            else  mainWagon=true;
            }
        System.out.println("wagons size "+wagons.size());
        //Consist Trains-------------------------------------------------------------
        String nameTrain = "train";
        int b;
        int sizeDepo = wagons.size() / 5;
        DepoWagon depoWagon;
        for (int i = 0; i < sizeDepo; i++) {
            Train train1 = new Train(nameTrain + "-" + i);
            //System.out.println("Train name= " + train1.getName());
            boolean a = true;
            Iterator<DepoWagon> depoWagonIterator=wagons.iterator();
            while (depoWagonIterator.hasNext() && a) {
                b = train1.trainSetting(depoWagonIterator.next());
                if (b == 1) {depoWagonIterator.remove();}
                if (train1.getCarriages().size() == 5) {a = false;}
            }
            trains.add(train1);
        }
    }
    //Completion metro driver-------------------------------------------------------------------------------------
    public void builderMetroDrivers (){
        //comparison of skills Driver
        Comparator<MetroDriver> metroDriverComparator=new Comparator<MetroDriver>() {
            @Override
            public int compare(MetroDriver o1, MetroDriver o2) {
               return -Double.compare(o1.getSkill(),o2.getSkill());
            }
        };
        // Metro Drivers
        PriorityQueue<MetroDriver> metroDriverPriorityQueue=new PriorityQueue<>(metroDriverComparator);
        String nameDriver="Driver";
        Random rand = new Random();
        for (int i = 0; i <20 ; i++) {
            metroDriverPriorityQueue.add(new MetroDriver(rand.nextInt(45),nameDriver+"-"+i));
        }
        this.metroDriverPriorityQueue=metroDriverPriorityQueue;
    }
    //Drivers distribute on trains and distribute metro lines -------------------------------------------------
    public void builderMetroLine(int countTrainsLine, LinkedList<Train> trains,
                                 PriorityQueue<MetroDriver> metroDriverPriorityQueue){
        LinkedList<MetroLine> metroLines=new LinkedList<>();
        Iterator<Train> trainIterator=trains.iterator();
        int countMetroDrivers=metroDriverPriorityQueue.size();
        Train trainLine;
        for (int i = 0; i < 3; i++) {
            int countStations=0;
            // metro Stations ----------------
            LinkedList<MetroStation> metroStations=new LinkedList<>();
            for (int j = 0; j <10 ; j++) {
                metroStations.add(j,new MetroStation(nameStations[i]+"-"+j));
            }
            //  trains  Line ---------------
            LinkedList<Train> trainsLine=new LinkedList<>();
            while (trainIterator.hasNext()){
                MetroDriver metroDriver=metroDriverPriorityQueue.poll();

                trainLine=trainIterator.next();
                trainLine.setMetroDriver(metroDriver);
                trainsLine.add(trainLine);
                metroDriver.Experience();
                //metroDrivers.add(metroDriver);
                countStations++;
                if (countStations>countTrainsLine) break;
                }
                metroLines.add(new MetroLine(nameStations[i],trainsLine,metroStations));

        }
        //DriverPriorityQueue fill
        for (int i = 0; i <countMetroDrivers ; i++) {
            metroDriverPriorityQueue.add(trains.get(i).getMetroDriver());

        }
        this.metroLines=metroLines;

        //System.out.println(metroDriverPriorityQueue);
    }

    //passengers go to Vestibule
    public void passengerGoVestibule(){
        Thread threadPassenger = new Thread(new Runnable() {
            @Override
            public void run() {
                String name ="passenger - ";
                int cuont =0;
                while (true){
                    Passenger passenger=new Passenger(name+cuont);
                    cuont++;
                    passengersOnVestibule.add(passenger);
                    if (passengersOnVestibule.size()>0)
                    System.out.println(" pas on Vestibule "+passengersOnVestibule.getLast());
                    metroLines.get(0).getMetroStations().get(0).setPassengersOnVestibule(passengersOnVestibule);
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadPassenger.start();
    }

    //passengers go to Escalator
    public void passengerGoEscalator(int i) throws InterruptedException {
        String name="escalator "+i;
        Escalator escalator=new Escalator(name);
        escalators.add(escalator);
        metroLines.get(0).getMetroStations().get(0).setEscalators(escalators);
        Thread threadPassenger = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                        escalator.sinhronizedThreade(metroLines,passengersOnEscalator,escalators,i);
                }
            }
        });
        Thread.sleep(3333);
        threadPassenger.start();
    }

    //
    public void trainOnStation(){
        Iterator<Train> trainIteratorOnLine=metroLines.get(0).getTrainsOnLine().iterator();
        Train trainOnLine=trainIteratorOnLine.next();
        int numberEscalator=0;
        Thread threadTrains =new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (metroLines.get(0).getMetroStations().get(0).getEscalators().get(0).getPassengers()) {
                        if (!metroLines.get(0).getMetroStations().get(0).getEscalators().get(0).getPassengers().isEmpty()) {
                            passengersOnTrain.add(metroLines.get(0).getMetroStations().get(0).getEscalators().get(0).getPassengers().poll());
                            trainOnLine.getCarriages().get(0).setPassengers(passengersOnTrain);
                            System.out.println("passengers On Train from 0 Escalator " + passengersOnTrain.getLast()+
                                    " "+trainOnLine.getCarriages().get(0).getPassengers().getLast());
                        }
                    }

                        if (!metroLines.get(0).getMetroStations().get(0).getEscalators().get(1).getPassengers().isEmpty()) {
                            synchronized (metroLines.get(0).getMetroStations().get(0).getEscalators().get(1).getPassengers()) {
                                passengersOnTrain.add(metroLines.get(0).getMetroStations().get(0).getEscalators().get(1).getPassengers().poll());
                                trainOnLine.getCarriages().get(0).setPassengers(passengersOnTrain);
                                System.out.println("passengers On Train from 1 Escalator " + passengersOnTrain.getLast()+
                                        " "+trainOnLine.getCarriages().get(0).getPassengers().getLast());
                            }
                        }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        threadTrains.start();


    }


}

