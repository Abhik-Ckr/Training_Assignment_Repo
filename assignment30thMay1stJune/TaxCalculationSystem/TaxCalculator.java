package assignment30thMay1stJune.TaxCalculationSystem;
import java.util.Scanner;

public class TaxCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Annual Salary (INR): ");
        double salary = sc.nextDouble();

        System.out.print("Enter Age (in years): ");
        int age = sc.nextInt();

        System.out.print("Enter Investment in Tax-saving Instruments (INR): ");
        double investment = sc.nextDouble();

        System.out.print("Enter Health Insurance Premium (INR): ");
        double insurance = sc.nextDouble();

        System.out.print("Enter Home Loan Interest Paid (INR): ");
        double homeLoanInterest = sc.nextDouble();

        TaxPayer taxpayer = new TaxPayer(salary, age, investment, insurance, homeLoanInterest);
        double tax = taxpayer.calculateTax();

        System.out.printf("Total Tax Owed: ₹%.2f\n", tax);
        sc.close();
    }
}
