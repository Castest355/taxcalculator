/*package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.contract.Contract;
import com.bartoszwalter.students.taxes.contract.ContractFactory;
import com.bartoszwalter.students.taxes.contract.TaxCalculationResult;
import com.bartoszwalter.students.taxes.report.TaxReportPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class TaxCalculatorApp {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            double income = readIncome(input);
            char contractCode = readContractCode(input);
            run(income, contractCode);
        } catch (IOException | NumberFormatException | StringIndexOutOfBoundsException exception) {
            System.out.println("Incorrect input");
            System.err.println(exception);
        }
    }

    private static double readIncome(BufferedReader input) throws IOException {
        System.out.print("Enter income: ");
        return Double.parseDouble(input.readLine());
    }

    private static char readContractCode(BufferedReader input) throws IOException {
        System.out.print("Contract Type: (E)mployment, (C)ivil: ");
        return input.readLine().charAt(0);
    }

    private static void run(double income, char contractCode) {
        Optional<Contract> contract = ContractFactory.fromCode(contractCode, income);
        if (contract.isEmpty()) {
            System.out.println("Unknown type of contract!");
            return;
        }
        TaxCalculationResult result = contract.get().calculateTaxes();
        new TaxReportPrinter(System.out).print(contract.get().getName(), result);
    }
}
*/
package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.contract.Contract;
import com.bartoszwalter.students.taxes.contract.ContractFactory;
import com.bartoszwalter.students.taxes.contract.TaxCalculationResult;
import com.bartoszwalter.students.taxes.report.TaxReportPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class TaxCalculatorApp {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in)
        );

        try {
            double income = readIncome(input);
            char contractCode = readContractCode(input);

            run(income, contractCode);

        } catch (IOException exception) {
            System.err.println("Error reading input: " + exception.getMessage());

        } catch (IllegalArgumentException exception) {
            System.err.println("Incorrect input: " + exception.getMessage());
        }
    }

    private static double readIncome(BufferedReader input) throws IOException {
        System.out.print("Enter income: ");
        return Double.parseDouble(input.readLine());
    }

    private static char readContractCode(BufferedReader input) throws IOException {
        System.out.print("Contract Type: (E)mployment, (C)ivil: ");

        String contractCode = input.readLine();

        if (contractCode == null || contractCode.isEmpty()) {
            throw new IllegalArgumentException("Contract type cannot be empty");
        }

        return contractCode.charAt(0);
    }

    private static void run(double income, char contractCode) {
        Optional<Contract> contract = ContractFactory.fromCode(contractCode, income);

        if (contract.isEmpty()) {
            System.out.println("Unknown type of contract!");
            return;
        }

        Contract selectedContract = contract.get();
        TaxCalculationResult result = selectedContract.calculateTaxes();

        TaxReportPrinter printer = new TaxReportPrinter(System.out);
        printer.print(selectedContract.getName(), result);
    }
}