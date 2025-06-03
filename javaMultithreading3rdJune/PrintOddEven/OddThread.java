package javaMultithreading3rdJune.PrintOddEven;

public class OddThread extends Thread{
    OddEvenPrinter printer;

    public OddThread(OddEvenPrinter printer){
        this.printer = printer;
    }
    public void run() {
        printer.printOdd();
    }
}
