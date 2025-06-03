package javaMultithreading3rdJune.BridgeCrossing;

public class Person extends Thread{
    String city;
    Bridge bridge;

    Person(String name, String city, Bridge bridge){
        super(name);
        this.city = city;
        this.bridge = bridge;
    }

    public void run(){
        bridge.cross(city, getName());
    }
}
