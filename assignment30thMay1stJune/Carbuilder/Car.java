package assignment30thMay1stJune.Carbuilder;

public class Car {
    String manufacturer;
    String model;
    String transmission;
    String fuelType;
    String color;
    String location;

    public Car(String manufacturer, String model, String transmission, String fuelType, String color, String location) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.color = color;
        this.location = location;
    }

    public void displayOptions() {
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
        System.out.println("Transmission: " + transmission);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Color: " + color);
        System.out.println("Location: " + location);
    }
}