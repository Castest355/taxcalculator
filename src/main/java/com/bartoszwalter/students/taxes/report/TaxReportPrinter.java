package com.bartoszwalter.students.taxes.report;

import com.bartoszwalter.students.taxes.contract.SocialSecurity;
import com.bartoszwalter.students.taxes.contract.TaxCalculationResult;

import java.io.PrintStream;
import java.text.DecimalFormat;

/** Presentation only: turns a TaxCalculationResult into a text report. */
public class TaxReportPrinter {

    private static final DecimalFormat TWO_DECIMALS = new DecimalFormat("0.00");
    private static final DecimalFormat NO_DECIMALS = new DecimalFormat("0");

    private final PrintStream out;

    public TaxReportPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(String contractName, TaxCalculationResult result) {
        out.println(contractName);
        printLine("Income", result.grossIncome());
        printSocialSecurity(result.socialSecurity());
        printLine("Income basis for health insurance", result.taxBasis());
        out.println("Health insurance: 9% = " + money(result.healthInsurance())
                + ", 7.75% = " + money(result.deductibleHealthInsurance()));
        printLine("Tax deductible expenses", result.deductibleExpenses());
        out.println("Taxable income = " + money(result.taxableIncome())
                + ", rounded = " + rounded(result.roundedTaxableIncome()));
        printLine("Advance tax 18%", result.advanceTax());
        printLine("Tax-free amount", result.taxFreeAmount());
        printLine("Reduced tax", result.reducedTax());
        out.println("Advance tax to pay = " + money(result.advanceTaxToPay())
                + ", rounded = " + rounded(result.roundedAdvanceTaxToPay()));
        out.println();
        printLine("Net income", result.netIncome());
    }

    private void printSocialSecurity(SocialSecurity socialSecurity) {
        printLine("Pension insurance", socialSecurity.pension());
        printLine("Disability insurance", socialSecurity.disability());
        printLine("Sickness insurance", socialSecurity.sickness());
    }

    private void printLine(String label, double amount) {
        out.println(label + " = " + money(amount));
    }

    private static String money(double amount) {
        return TWO_DECIMALS.format(amount);
    }

    private static String rounded(double amount) {
        return NO_DECIMALS.format(amount);
    }
}
