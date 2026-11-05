package com.bartoszwalter.students.taxes.contract;

/** Immutable result of a tax calculation. Contains values only, no printing logic. */
public final class TaxCalculationResult {

    private final double grossIncome;
    private final SocialSecurity socialSecurity;
    private final double taxBasis;
    private final double healthInsurance;
    private final double deductibleHealthInsurance;
    private final double deductibleExpenses;
    private final double taxableIncome;
    private final double roundedTaxableIncome;
    private final double advanceTax;
    private final double taxFreeAmount;
    private final double reducedTax;
    private final double advanceTaxToPay;
    private final double roundedAdvanceTaxToPay;
    private final double netIncome;

    TaxCalculationResult(double grossIncome, SocialSecurity socialSecurity, double taxBasis,
                         double healthInsurance, double deductibleHealthInsurance,
                         double deductibleExpenses, double taxableIncome, double roundedTaxableIncome,
                         double advanceTax, double taxFreeAmount, double reducedTax,
                         double advanceTaxToPay, double roundedAdvanceTaxToPay, double netIncome) {
        this.grossIncome = grossIncome;
        this.socialSecurity = socialSecurity;
        this.taxBasis = taxBasis;
        this.healthInsurance = healthInsurance;
        this.deductibleHealthInsurance = deductibleHealthInsurance;
        this.deductibleExpenses = deductibleExpenses;
        this.taxableIncome = taxableIncome;
        this.roundedTaxableIncome = roundedTaxableIncome;
        this.advanceTax = advanceTax;
        this.taxFreeAmount = taxFreeAmount;
        this.reducedTax = reducedTax;
        this.advanceTaxToPay = advanceTaxToPay;
        this.roundedAdvanceTaxToPay = roundedAdvanceTaxToPay;
        this.netIncome = netIncome;
    }

    public double grossIncome() { return grossIncome; }
    public SocialSecurity socialSecurity() { return socialSecurity; }
    public double taxBasis() { return taxBasis; }
    public double healthInsurance() { return healthInsurance; }
    public double deductibleHealthInsurance() { return deductibleHealthInsurance; }
    public double deductibleExpenses() { return deductibleExpenses; }
    public double taxableIncome() { return taxableIncome; }
    public double roundedTaxableIncome() { return roundedTaxableIncome; }
    public double advanceTax() { return advanceTax; }
    public double taxFreeAmount() { return taxFreeAmount; }
    public double reducedTax() { return reducedTax; }
    public double advanceTaxToPay() { return advanceTaxToPay; }
    public double roundedAdvanceTaxToPay() { return roundedAdvanceTaxToPay; }
    public double netIncome() { return netIncome; }
}
