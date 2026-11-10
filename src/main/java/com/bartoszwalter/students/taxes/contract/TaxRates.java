package com.bartoszwalter.students.taxes.contract;

/**
 * Defines the tax rates and fixed amounts used in tax calculations.
 * Percentage rates are expressed as decimal fractions (e.g., 0.0976 = 9.76%).
 */
public final class TaxRates {

    // Social security contribution rates
    public static final double PENSION_INSURANCE_RATE = 0.0976;
    public static final double DISABILITY_INSURANCE_RATE = 0.015;
    public static final double SICKNESS_INSURANCE_RATE = 0.0245;

    // Health insurance rates
    public static final double HEALTH_INSURANCE_RATE = 0.09;
    public static final double HEALTH_INSURANCE_DEDUCTIBLE_RATE = 0.0775;

    // Income tax rate
    public static final double ADVANCE_INCOME_TAX_RATE = 0.18;

    // Employment contract deductions
    public static final double EMPLOYMENT_DEDUCTIBLE_EXPENSES = 111.25;
    public static final double EMPLOYMENT_MONTHLY_TAX_FREE_AMOUNT = 46.33;

    // Civil contract deductions
    public static final double CIVIL_DEDUCTIBLE_EXPENSES_RATE = 0.20;
    public static final double CIVIL_MONTHLY_TAX_FREE_AMOUNT = 0.0;

    private TaxRates() {
    }
}