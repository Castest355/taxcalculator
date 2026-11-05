package com.bartoszwalter.students.taxes.contract;

public class EmploymentContract extends Contract {

    public EmploymentContract(double grossIncome) {
        super(grossIncome);
    }

    @Override
    public String getName() {
        return "EMPLOYMENT";
    }

    @Override
    protected double deductibleExpenses(double taxBasis) {
        return TaxRates.EMPLOYMENT_DEDUCTIBLE_EXPENSES;
    }

    @Override
    protected double taxFreeAmount() {
        return TaxRates.EMPLOYMENT_MONTHLY_TAX_FREE_AMOUNT;
    }
}
