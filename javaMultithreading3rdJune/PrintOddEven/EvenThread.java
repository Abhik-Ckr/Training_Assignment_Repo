package javaMultithreading3rdJune.PrintOddEven;

public class EvenThread extends Thread{
    OddEvenPrinter printer;

    EvenThread(OddEvenPrinter printer){
        this.printer = printer;
    }

    public void run() {
        printer.printEven();
    }
}
