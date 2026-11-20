package com.losantos.models;

import java.time.LocalDate;

/**
 * MISSION 3: Calculate the lease amount and its 36-month payment.
 */
public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        // TODO: price - 50% expected ending value + 7% lease fee.
        // Total Lease Amount Formula:
        // Total Lease Amount = Vehicle Price - Expected Ending Value + Lease Fee

        if (isNotLeaseEligible()) System.exit(-1);

        double originalVehiclePrice = super.getVehicle().getPrice();

        double expectedEndingValue = originalVehiclePrice * 0.50;

        double leaseFee = originalVehiclePrice * 0.07;


        return (originalVehiclePrice - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        // TODO: amortize getTotalPrice() at 4% annual interest over 36 months.
        if (isNotLeaseEligible()) System.exit(-1);

        double originalVehiclePrice = super.getVehicle().getPrice();

         /*
          Standard Loan Payment Formula:
          M = P * r / (1 - (1 + r)^(-n))
          Where:
          ● M = monthly payment
          ● P = total amount financed (principal)
          ● r = monthly interest rate (0.04 / 12)
          ● n = number of monthly payments
         */

        double principal = getTotalPrice();

        double vehiclePrice = super.getVehicle().getPrice();
        double annualInterestRate = 0.04 / 12;
        int months = 36;

        double monthlyRate = annualInterestRate / 12.0;

        return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months)); // monthly payment
    }

    private boolean isNotLeaseEligible() {
        // TODO: Vehicles older than three years cannot be leased. Before creating the contract, compare the vehicle's model year with the current year.
        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        int vehicleOriginYear = super.getVehicle().getYear();

        int currentVehicleAge = currentYear - vehicleOriginYear;

        if (vehicleOriginYear < 3) {
            System.err.println("ERROR: Vehicle is too old for leasing.\nOnly vehicles up to 3 years old are eligible.");
            return true;
        }

        return false;
    }
}
