package com.bartoszwalter.students.taxes.contract;

/**
 * Common tax algorithm shared by every contract type (Template Method).
 * Subclasses only provide what differs: deductible expenses and the tax-free amount.
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
        double taxBasis = grossIncome - socialSecurity.total();

        double healthInsurance = taxBasis * TaxRates.HEALTH_INSURANCE;
        double deductibleHealthInsurance = taxBasis * TaxRates.HEALTH_INSURANCE_DEDUCTIBLE;

        double deductibleExpenses = deductibleExpenses(taxBasis);
        double taxableIncome = taxBasis - deductibleExpenses;
        double roundedTaxableIncome = Math.round(taxableIncome);

        double advanceTax = roundedTaxableIncome * TaxRates.ADVANCE_INCOME_TAX;
        double taxFreeAmount = taxFreeAmount();
        double reducedTax = advanceTax - taxFreeAmount;
        double advanceTaxToPay = advanceTax - deductibleHealthInsurance - taxFreeAmount;
        double roundedAdvanceTaxToPay = Math.round(advanceTaxToPay);

        double netIncome = grossIncome - socialSecurity.total() - healthInsurance - roundedAdvanceTaxToPay;

        return new TaxCalculationResult(
                grossIncome, socialSecurity, taxBasis,
                healthInsurance, deductibleHealthInsurance,
                deductibleExpenses, taxableIncome, roundedTaxableIncome,
                advanceTax, taxFreeAmount, reducedTax,
                advanceTaxToPay, roundedAdvanceTaxToPay, netIncome);
    }
}
