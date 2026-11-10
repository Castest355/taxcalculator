package com.bartoszwalter.students.taxes.contract;

/**
 * Defines the tax rules for civil contracts.
 */
public final class CivilContract extends Contract {

    public CivilContract(double grossIncome) {
        super(grossIncome);
    }

    @Override
    public String getName() {
        return "CIVIL";
    }

    @Override
    protected double deductibleExpenses(double taxBasis) {
        return taxBasis * TaxRates.CIVIL_DEDUCTIBLE_EXPENSES_RATE;
    }

    @Override
    protected double taxFreeAmount() {
        return TaxRates.CIVIL_MONTHLY_TAX_FREE_AMOUNT;
    }
}