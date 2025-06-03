package javaMultithreading3rdJune.BridgeCrossing;

public class BridgeManagement {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        for (int i = 1; i <= 5; i++) {
            new Person("PersonB" + i, "CityB", bridge).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Person("PersonA" + i, "CityA", bridge).start();
        }
    }
}
