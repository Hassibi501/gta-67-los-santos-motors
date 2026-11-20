package com.losantos.models;

/**
 * MISSION 2: Calculate tax, fees, and optional financed monthly payments.
 * <p>
 * Hint: Vehicle data isn't stored directly on SalesContract—check the parent
 * class (Contract) to see how to access the vehicle and its price.
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
        // TODO: Look up the vehicle price via the superclass and add:
        //   - 5% sales tax
        //   - $100 recording fee
        //   - processing fee ($295 under $10,000, otherwise $495)
        // Hint: Keep tax separate from base vehicle price so you don't double-count it.
        throw new UnsupportedOperationException("TODO: implement getTotalPrice()");
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

        // TODO: If the contract is not financed, return 0.
        // TODO: Otherwise, amortize getTotalPrice() using the rate and term
        //       tier based on vehicle price (check Contract for the vehicle getter).
        // Hint: Convert annual percentage rates to a decimal monthly rate before
        //       using Math.pow(...).
        throw new UnsupportedOperationException("TODO: implement getMonthlyPayment()");
    }
}