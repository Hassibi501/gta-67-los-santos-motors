package com.losantos.data;

import com.losantos.models.Contract;
import com.losantos.models.LeaseContract;
import com.losantos.models.SalesContract;
import com.losantos.models.Vehicle;

import java.io.IOException;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.io.FileWriter;

/** MISSION 4: Append sale and lease contracts to a single file. */
public class ContractFileManager {
    private final Path file;

    public ContractFileManager(Path file) { this.file = file; }

    public void saveContract(Contract contract) throws IOException {
        // TODO: use instanceof to distinguish SalesContract from LeaseContract.
        // TODO: format ALL required common and contract-specific fields separated by '|'.
        // TODO: append one record using Files.writeString(..., CREATE, APPEND).
        // IMPORTANT: do NOT overwrite older records.

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("contracts.csv", true)
        )) {
            StringBuilder record = new StringBuilder();

            Vehicle vehicle = contract.getVehicle();

            if (contract instanceof SalesContract sales) {

                record.append("SALE").append("|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(vehicle.getVin()).append("|")
                        .append(vehicle.getYear()).append("|")
                        .append(vehicle.getMake()).append("|")
                        .append(vehicle.getModel()).append("|")
                        .append(vehicle.getColor()).append("|")
                        .append(vehicle.getOdometer()).append("|")
                        .append(vehicle.getPrice()).append("|")
                        .append(sales.getTotalPrice()).append("|")
                        .append(sales.getMonthlyPayment()).append("|");
            } else if (contract instanceof LeaseContract lease) {

                record.append("LEASE").append("|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(vehicle.getVin()).append("|")
                        .append(vehicle.getYear()).append("|")
                        .append(vehicle.getMake()).append("|")
                        .append(vehicle.getModel()).append("|")
                        .append(vehicle.getVehicleType()).append("|")
                        .append(vehicle.getColor()).append("|")
                        .append(vehicle.getOdometer()).append("|")
                        .append(vehicle.getPrice()).append("|")
                        .append(lease.getTotalPrice()).append("|")
                        .append(lease.getMonthlyPayment()).append("|");
                 }
            writer.write(record.toString());
            writer.newLine();

            } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }

    }
}
