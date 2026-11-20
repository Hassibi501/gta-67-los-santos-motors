package com.losantos.models;

import java.time.Year;
/**
 * MISSION 3: Calculate the lease amount and its 36-month payment.
 */
public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, requireEligibleVehicle(vehicle));
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        super.setVehicle(requireEligibleVehicle(vehicle));

    }

    public double getExpectedEndingValue() {
        return getVehicle().getPrice() * 0.50;
    }

    public double getLeaseFee() {
        return getVehicle().getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        double originalVehiclePrice = getVehicle().getPrice();
        return (originalVehiclePrice - getExpectedEndingValue()) + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
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
        double monthlyRate = 0.04 / 12.0;
        int months = 36;

        return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months)); // monthly payment
    }

    private static Vehicle requireEligibleVehicle(Vehicle vehicle) {
        int currentYear = Year.now().getValue();
        int vehicleAge = currentYear - vehicle.getYear();

        if (vehicleAge > 3) {
            throw new IllegalArgumentException(
                    "Only vehicles up to 3 model years old are eligible for leasing.");
        }

        return vehicle;

    }
}
