package com.bartoszwalter.students.taxes.contract;

/**
 * Immutable result of a complete tax calculation.
 *
 * Groups the results of each calculation stage.
 */
public final class TaxCalculationResult {

    private final double grossIncome;
    private final SocialSecurity socialSecurity;
    private final HealthInsuranceResult healthInsurance;
    private final IncomeTaxResult incomeTax;
    private final double netIncome;

    TaxCalculationResult(
            double grossIncome,
            SocialSecurity socialSecurity,
            HealthInsuranceResult healthInsurance,
            IncomeTaxResult incomeTax,
            double netIncome
    ) {
        this.grossIncome = grossIncome;
        this.socialSecurity = socialSecurity;
        this.healthInsurance = healthInsurance;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
    }

    public double grossIncome() {
        return grossIncome;
    }

    public SocialSecurity socialSecurity() {
        return socialSecurity;
    }

    public HealthInsuranceResult healthInsurance() {
        return healthInsurance;
    }

    public IncomeTaxResult incomeTax() {
        return incomeTax;
    }

    public double netIncome() {
        return netIncome;
    }
}