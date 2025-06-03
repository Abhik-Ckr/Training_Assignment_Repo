package javaMultithreading3rdJune.BridgeCrossing;

public class Bridge {
    private boolean isTokenInCityB = true;
    public synchronized void cross(String city, String personName) {
        try {
            while ((city.equals("CityA") && isTokenInCityB) || (city.equals("CityB") && !isTokenInCityB)) {
                wait();
            }
            System.out.println(personName + " from " + city + " is crossing the bridge.");
            Thread.sleep(1000);

            isTokenInCityB = !isTokenInCityB;
            System.out.println(personName + " reached opposite city. Token moved to " + (isTokenInCityB ? "CityB" : "CityA"));
            notifyAll();
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
