package javaMultithreading3rdJune.PrintOddEven;

public class OddEvenPrinter {
    int max;
    int number = 1;

    OddEvenPrinter(int max){
        this.max = max;
    }
    public synchronized void printOdd() {
        while (number <= max) {
            if (number % 2 == 1) {
                System.out.println("Odd: " + number++);
                notify();
            } else {
                waitForTurn();
            }
        }
        notify();
    }

    public synchronized void printEven() {
        while (number <= max) {
            if (number % 2 == 0) {
                System.out.println("Even: " + number++);
                notify();
            } else {
                waitForTurn();
            }
        }
        notify(); // ensure to wake up waiting thread
    }
    private void waitForTurn() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

