package com.bartoszwalter.students.taxes.contract;

/**
 * Defines the common tax calculation algorithm.
 *
 * Uses the Template Method pattern to allow subclasses
 * to provide contract-specific tax rules.
 */
public abstract class Contract {

    private final double grossIncome;

    protected Contract(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public abstract String getName();

    protected abstract double deductibleExpenses(double taxBasis);

    protected abstract double taxFreeAmount();

    public final TaxCalculationResult calculateTaxes() {

        SocialSecurity socialSecurity = SocialSecurity.calculateFor(grossIncome);
        double totalSocialSecurity = socialSecurity.total();

        double taxBasis = grossIncome - totalSocialSecurity;

        double healthInsurance =
                taxBasis * TaxRates.HEALTH_INSURANCE_RATE;

        double deductibleHealthInsurance =
                taxBasis * TaxRates.HEALTH_INSURANCE_DEDUCTIBLE_RATE;

        HealthInsuranceResult healthInsuranceResult =
                new HealthInsuranceResult(
                        taxBasis,
                        healthInsurance,
                        deductibleHealthInsurance
                );

        double deductibleExpenses = deductibleExpenses(taxBasis);

        double taxableIncome = taxBasis - deductibleExpenses;
        double roundedTaxableIncome = Math.round(taxableIncome);

        double advanceTax =
                roundedTaxableIncome * TaxRates.ADVANCE_INCOME_TAX_RATE;

        double taxFreeAmount = taxFreeAmount();

        double reducedTax = advanceTax - taxFreeAmount;

        double advanceTaxToPay =
                advanceTax - deductibleHealthInsurance - taxFreeAmount;

        double roundedAdvanceTaxToPay = Math.round(advanceTaxToPay);

        IncomeTaxResult incomeTaxResult =
                new IncomeTaxResult(
                        deductibleExpenses,
                        taxableIncome,
                        roundedTaxableIncome,
                        advanceTax,
                        taxFreeAmount,
                        reducedTax,
                        advanceTaxToPay,
                        roundedAdvanceTaxToPay
                );

        double netIncome =
                grossIncome
                - totalSocialSecurity
                - healthInsurance
                - roundedAdvanceTaxToPay;

        return new TaxCalculationResult(
                grossIncome,
                socialSecurity,
                healthInsuranceResult,
                incomeTaxResult,
                netIncome
        );
    }
}