package com.bartoszwalter.students.taxes.contract;

public class CivilContract extends Contract {

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
