package com.losantos.models;

/**
 * MISSION 2: Calculate tax, fees, and optional financed monthly payments.
 */
public class SalesContract extends Contract {
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = super.getVehicle().getPrice();

        double salesTax = vehiclePrice * 1.05;
        double recordingFee = 100;

        double processingFee = (vehiclePrice < 10_000) ? 295 : 495;

        return salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        /*
          Standard Loan Payment Formula:
          M = P * r / (1 - (1 + r)^(-n))
          Where:
          ● M = monthly payment
          ● P = total amount financed (principal)
          ● r = monthly interest rate (annualInterestRate / 12)
          ● n = number of monthly payments
         */

        if (!this.finance) return 0;

        double principal = getTotalPrice();

        double vehiclePrice = super.getVehicle().getPrice();
        double annualInterestRate;
        int months;

        if (vehiclePrice < 10_000) {
            annualInterestRate = 5.25 / 100;
            months = 24;
        } else {
            annualInterestRate = 4.25 / 100;
            months = 48;
        }
        double monthlyRate = annualInterestRate / 12.0;

        return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months)); // monthly payment
    }
}
