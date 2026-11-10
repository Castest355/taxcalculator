package com.bartoszwalter.students.taxes.contract;

/**
 * Represents the social security contributions
 * calculated from gross income.
 */
public final class SocialSecurity {

    private final double pension;
    private final double disability;
    private final double sickness;

    private SocialSecurity(
            double pension,
            double disability,
            double sickness
    ) {
        this.pension = pension;
        this.disability = disability;
        this.sickness = sickness;
    }

    public static SocialSecurity calculateFor(double grossIncome) {

        return new SocialSecurity(
                grossIncome * TaxRates.PENSION_INSURANCE_RATE,
                grossIncome * TaxRates.DISABILITY_INSURANCE_RATE,
                grossIncome * TaxRates.SICKNESS_INSURANCE_RATE
        );
    }

    public double pension() {
        return pension;
    }

    public double disability() {
        return disability;
    }

    public double sickness() {
        return sickness;
    }

    public double total() {
        return pension + disability + sickness;
    }
}