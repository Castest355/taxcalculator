package com.bartoszwalter.students.taxes.contract;

/** Statutory rates and amounts used by the calculator, expressed as fractions (0.0976 = 9.76%). */
public final class TaxRates {

    public static final double PENSION_INSURANCE = 0.0976;
    public static final double DISABILITY_INSURANCE = 0.015;
    public static final double SICKNESS_INSURANCE = 0.0245;

    public static final double HEALTH_INSURANCE = 0.09;
    public static final double HEALTH_INSURANCE_DEDUCTIBLE = 0.0775;

    public static final double ADVANCE_INCOME_TAX = 0.18;

    public static final double EMPLOYMENT_DEDUCTIBLE_EXPENSES = 111.25;
    public static final double EMPLOYMENT_MONTHLY_TAX_FREE_AMOUNT = 46.33;

    public static final double CIVIL_DEDUCTIBLE_EXPENSES_RATE = 0.20;
    public static final double CIVIL_MONTHLY_TAX_FREE_AMOUNT = 0.0;

    private TaxRates() {
    }
}
