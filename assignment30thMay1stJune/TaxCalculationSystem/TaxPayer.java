package assignment30thMay1stJune.TaxCalculationSystem;

class TaxPayer {
    private double salary;
    private int age;
    private double investment;
    private double insurance;
    private double homeLoanInterest;

    public TaxPayer(double salary, int age, double investment, double insurance, double homeLoanInterest) {
        this.salary = salary;
        this.age = age;
        this.investment = investment;
        this.insurance = insurance;
        this.homeLoanInterest = homeLoanInterest;
    }

    public double calculateDeductions() {
        double section80C = Math.min(investment, 150000);
        double section80D;

        if (age < 60) {
            section80D = Math.min(insurance, 25000);
        } else {
            section80D = Math.min(insurance, 50000);
        }

        double section24 = Math.min(homeLoanInterest, 200000);

        return section80C + section80D + section24;
    }

    public double calculateTaxableIncome() {
        double deductions = calculateDeductions();
        double taxableIncome = salary - deductions;
        return Math.max(taxableIncome, 0); // Taxable income can't be negative
    }

    public double calculateTax() {
        double taxableIncome = calculateTaxableIncome();
        double tax = 0;

        if (age < 60) {
            if (taxableIncome <= 250000) {
                tax = 0;
            } else if (taxableIncome <= 500000) {
                tax = (taxableIncome - 250000) * 0.05;
            } else if (taxableIncome <= 1000000) {
                tax = 250000 * 0.05 + (taxableIncome - 500000) * 0.20;
            } else {
                tax = 250000 * 0.05 + 500000 * 0.20 + (taxableIncome - 1000000) * 0.30;
            }
        } else if (age < 80) {
            if (taxableIncome <= 300000) {
                tax = 0;
            } else if (taxableIncome <= 500000) {
                tax = (taxableIncome - 300000) * 0.05;
            } else if (taxableIncome <= 1000000) {
                tax = 200000 * 0.05 + (taxableIncome - 500000) * 0.20;
            } else {
                tax = 200000 * 0.05 + 500000 * 0.20 + (taxableIncome - 1000000) * 0.30;
            }
        } else {
            if (taxableIncome <= 500000) {
                tax = 0;
            } else if (taxableIncome <= 1000000) {
                tax = (taxableIncome - 500000) * 0.20;
            } else {
                tax = 500000 * 0.20 + (taxableIncome - 1000000) * 0.30;
            }
        }

        return tax;
    }
}
