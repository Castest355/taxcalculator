package com.bartoszwalter.students.taxes.contract;

/** Social security contributions computed from gross income. */
public final class SocialSecurity {

    private final double pension;
    private final double disability;
    private final double sickness;

    private SocialSecurity(double pension, double disability, double sickness) {
        this.pension = pension;
        this.disability = disability;
        this.sickness = sickness;
    }

    public static SocialSecurity calculateFor(double grossIncome) {
        return new SocialSecurity(
                grossIncome * TaxRates.PENSION_INSURANCE,
                grossIncome * TaxRates.DISABILITY_INSURANCE,
                grossIncome * TaxRates.SICKNESS_INSURANCE);
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
