package com.losantos.models;

import java.time.Year;

public class LeaseContractTest {
    public static void main(String[] args) {
        int currentYear = Year.now().getValue();
        String contractDate = currentYear + "-01-01";

        // testing a $20,000 vehicle exactly three model years old should qualify.
        Vehicle eligibleVehicle = new Vehicle(
                "TEST-1", currentYear - 3,
                "Toyota", "Camry", "Sedan", "Blue", 20_000, 20_000.00
        );

        LeaseContract lease = new LeaseContract(
                contractDate, "Test Customer", "test@example.com", eligibleVehicle

        );
        checkAmount("Expected ending value", 10_000.00, lease.getExpectedEndingValue());
        checkAmount("Lease fee", 1_400.00, lease.getLeaseFee());
        checkAmount("Lease total", 11_400.00, lease.getTotalPrice());

        double monthlyRate = 0.04 / 12.0;
        double expectedPayment = 11_400.00 * monthlyRate
                / (1 - Math.pow(1 + monthlyRate, -36));

        checkAmount("Unrounded monthly payment", expectedPayment, lease.getMonthlyPayment());


        Vehicle tooOld = new Vehicle(
                "TEST-2", currentYear - 4,
                "Toyota", "Camry", "Sedan", "Blue", 30_000, 20_000.00
        );
        try {
            new LeaseContract(
                    contractDate, "Test Customer", "test@example.com", tooOld
            );

            throw new AssertionError("A four-model-year-old vehicle was accepted.");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Vehicle four model years old was rejected.");
        }

            // new test
        try {
            lease.setVehicle(tooOld);
            throw new AssertionError("The lease accepted an ineligible replacement vehicle.");
        } catch (IllegalArgumentException expected) {
            System.out.println("PASS: Rejected an ineligible replacement vehicle.");
        }
            System.out.println("All lease contract checks passed.");
    }

    private static void checkAmount(String label, double expected, double actual) {
        // Allow for tiny differences from double arithmetic.
        if (!(Math.abs(expected - actual) < 0.000001)) {
            throw new AssertionError(label + " expected " + expected + " but got " + actual);

        }
        System.out.println("PASS: " + label);

    }
}
