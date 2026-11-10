package com.bartoszwalter.students.taxes.contract;

/**
 * Immutable result of income tax calculations.
 */
public final class IncomeTaxResult {

    private final double deductibleExpenses;
    private final double taxableIncome;
    private final double roundedTaxableIncome;
    private final double advanceTax;
    private final double taxFreeAmount;
    private final double reducedTax;
    private final double advanceTaxToPay;
    private final double roundedAdvanceTaxToPay;

    public IncomeTaxResult(
            double deductibleExpenses,
            double taxableIncome,
            double roundedTaxableIncome,
            double advanceTax,
            double taxFreeAmount,
            double reducedTax,
            double advanceTaxToPay,
            double roundedAdvanceTaxToPay
    ) {
        this.deductibleExpenses = deductibleExpenses;
        this.taxableIncome = taxableIncome;
        this.roundedTaxableIncome = roundedTaxableIncome;
        this.advanceTax = advanceTax;
        this.taxFreeAmount = taxFreeAmount;
        this.reducedTax = reducedTax;
        this.advanceTaxToPay = advanceTaxToPay;
        this.roundedAdvanceTaxToPay = roundedAdvanceTaxToPay;
    }

    public double deductibleExpenses() {
        return deductibleExpenses;
    }

    public double taxableIncome() {
        return taxableIncome;
    }

    public double roundedTaxableIncome() {
        return roundedTaxableIncome;
    }

    public double advanceTax() {
        return advanceTax;
    }

    public double taxFreeAmount() {
        return taxFreeAmount;
    }

    public double reducedTax() {
        return reducedTax;
    }

    public double advanceTaxToPay() {
        return advanceTaxToPay;
    }

    public double roundedAdvanceTaxToPay() {
        return roundedAdvanceTaxToPay;
    }
}