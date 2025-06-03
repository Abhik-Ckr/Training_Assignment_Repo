package javaMultithreading3rdJune.PrintOddEven;

public class Main {
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter(10);
        new OddThread(printer).start();
        new EvenThread(printer).start();
    }
}
