package com.bartoszwalter.students.taxes.contract;

/**
 * Immutable result of health insurance calculations.
 */
public final class HealthInsuranceResult {

    private final double taxBasis;
    private final double healthInsurance;
    private final double deductibleHealthInsurance;

    public HealthInsuranceResult(
            double taxBasis,
            double healthInsurance,
            double deductibleHealthInsurance
    ) {
        this.taxBasis = taxBasis;
        this.healthInsurance = healthInsurance;
        this.deductibleHealthInsurance = deductibleHealthInsurance;
    }

    public double taxBasis() {
        return taxBasis;
    }

    public double healthInsurance() {
        return healthInsurance;
    }

    public double deductibleHealthInsurance() {
        return deductibleHealthInsurance;
    }
}