package assignment30thMay1stJune.Carbuilder;

import java.util.Scanner;


public class Carbuilder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select Manufacturer (Mahindra/Tata/Maruti): ");
        String manufacturer = sc.nextLine().trim();

        System.out.println("Enter Model: ");
        String model = sc.nextLine().trim();

        System.out.println("Enter Transmission Type (Manual/Automatic): ");
        String transmission = sc.nextLine().trim();

        System.out.println("Enter Fuel Type (Petrol/Diesel/CNG): ");
        String fuelType = sc.nextLine().trim();

        System.out.println("Enter Color (Silver/Blue/Yellow): ");
        String color = sc.nextLine().trim();

        System.out.println("Enter Location (Delhi/Bangalore/Hyderabad/Chennai): ");
        String location = sc.nextLine().trim();

        Car car;

        if (manufacturer.equalsIgnoreCase("mahindra")) {
            car = new Mahindra(model, transmission, fuelType, color, location);
        } else if (manufacturer.equalsIgnoreCase("tata")) {
            car = new Tata(model, transmission, fuelType, color, location);
        } else if (manufacturer.equalsIgnoreCase("maruti")) {
            car = new Maruti(model, transmission, fuelType, color, location);
        } else {
            System.out.println("Invalid manufacturer. Please choose from Mahindra, Tata, or Maruti.");
            return;
        }

        System.out.println("\n--- Selected Car Configuration ---");
        car.displayOptions();
    }
}