# TaxCalculator – Clean Code Refactoring

**Author:** Sergio Álvarez
**Course:** Clean Code – Assignment 1 (Poznań University of Technology)
**Original code:** https://github.com/bartoszwalter/taxcalculator

---

## 1. Starting point

The original `TaxCalculator.java` is a single class with one ~100-line `main` method, global
mutable `static` state and copy-pasted branches for each contract type.

**The original does not even compile** (`javac` reports 10+ errors):

| Problem | Example |
|---|---|
| Field declared twice | `advanceTaxPaidadvanceTax` (lines 19 and 21) |
| Undeclared variables | `advanceTax`, `advanceTaxPaid`, `taxPaid`, `soc_sick_secur` |
| Local variable shadowing a field and reading itself | `double income = calculateIncome(income);` |
| Calculated value never used | `double d_income = calculateIncome(income);` |

So step zero was to work out the **intended behaviour** from the variable names, comments and
the method bodies, and then preserve that behaviour through all refactorings.

### Business rules recovered from the original

1. Social security = 9.76% + 1.5% + 2.45% of gross income.
2. Tax basis = gross income − social security.
3. Health insurance = 9% of the basis (paid) and 7.75% of the basis (deducted from tax).
4. Deductible expenses: **employment** = fixed 111.25 PLN; **civil** = 20% of the basis.
5. Taxable income = basis − deductible expenses, rounded to whole PLN.
6. Advance tax = 18% of the rounded taxable income.
7. Tax-free amount: **employment** = 46.33 PLN; **civil** = 0.
8. Advance tax to pay = advance tax − 7.75% health insurance − tax-free amount, rounded.
9. Net income = gross − social security − 9% health insurance − rounded advance tax to pay.

---

## 2. Final structure

```
src/main/java/com/bartoszwalter/students/taxes/
├── TaxCalculatorApp.java          ← input/output only (entry point)
├── contract/
│   ├── Contract.java              ← abstract: common algorithm (Template Method)
│   ├── EmploymentContract.java    ← what is specific to employment
│   ├── CivilContract.java         ← what is specific to civil contracts
│   ├── ContractFactory.java       ← maps 'E' / 'C' to a contract object
│   ├── SocialSecurity.java        ← value object for the 3 contributions
│   ├── TaxCalculationResult.java  ← immutable result (data, no printing)
│   └── TaxRates.java              ← every magic number, named, in one place
└── report/
    └── TaxReportPrinter.java      ← formatting and printing only
```

---

## 3. Changes and justification

Changes are listed in the order they were applied (from simple to structural).

### 3.1 Naming
| Before | After | Why |
|---|---|---|
| `soc_security`, `soc_health_security`, `soc_sick_security` | `pension`, `disability`, `sickness` (in `SocialSecurity`) | Names must reveal intent; Java uses camelCase, not snake_case. `soc_health_security` was actually the disability contribution – a misleading name. |
| `soc_health1`, `soc_health2` | `healthInsurance`, `deductibleHealthInsurance` | Number-series names (`1`, `2`) say nothing about meaning. |
| `advanceTaxPaidadvanceTax`, `advanceTaxPaid0`, `taxedIncome0` | `advanceTaxToPay`, `roundedAdvanceTaxToPay`, `roundedTaxableIncome` | Avoid noise words and digit suffixes; the name says *it is rounded*. |
| `calculateIncome()` | `SocialSecurity.calculateFor()` + `taxBasis` | The method computed social security, not "income". |
| `calculateOtherTaxes()` | removed (inlined into `Contract`) | "Other" is a meaningless name. |
| `df00`, `df`, `isr`, `br` | `TWO_DECIMALS`, `NO_DECIMALS`, `input` | Avoid cryptic abbreviations. |
| `contractType == 'E'` | `ContractFactory.fromCode(...)` | Encapsulates the encoding. |

### 3.2 Magic numbers → named constants (`TaxRates`)
`9.76`, `1.5`, `2.45`, `9`, `7.75`, `18`, `20`, `111.25`, `46.33` were scattered across the code
and partly duplicated. They are now named constants in one class. If the law changes, only one
place has to change, and the code reads like the business rule
(`taxBasis * TaxRates.HEALTH_INSURANCE`).

### 3.3 Comments
Comments like `// 9,76% of basis` were only there to explain bad names – and some were wrong
(`// advance tax 18%` next to `advanceTaxPaidadvanceTax`). With intention-revealing names
the comments became redundant and were removed. The only comments left are short class-level
Javadoc that explain *why* a class exists, not *what* a line does.

### 3.4 Global mutable state removed
All fields were `public static` and modified from several methods, so the result depended on the
order of calls (e.g. the civil branch overwrote `taxFreeIncome = 0` and `taxDeductibleExpenses`,
changing global state). Now every value is a local variable or a `final` field of an immutable
object. Methods have no hidden side effects and can be tested independently.

### 3.5 Function length and Single Responsibility
`main` mixed four responsibilities: reading input, choosing the contract, calculating and
printing. It is split into:

* `TaxCalculatorApp` – reads input, delegates (methods of 3–10 lines);
* `Contract.calculateTaxes()` – calculation only, returns a result object;
* `TaxReportPrinter` – presentation only.

Calculations no longer call `System.out`, so the same logic could be reused by a GUI, a REST API
or a unit test.

### 3.6 Duplication removed – inheritance + Template Method
The `if ('E') … else if ('C') …` branches were ~40 lines each and ~90% identical.
I created an abstract base class `Contract` with the shared algorithm in a `final` method
`calculateTaxes()`. Subclasses only override the two things that actually differ:

```java
protected abstract double deductibleExpenses(double taxBasis);
protected abstract double taxFreeAmount();
```

* **DRY:** the algorithm exists once.
* **Open/Closed Principle:** the assignment says *"maybe something else in the future"*. Adding a new
  contract now means one new subclass + one line in `ContractFactory`; `Contract`,
  the printer and `main` stay untouched.
* Conditional complexity is replaced by polymorphism: cyclomatic complexity of `main` dropped
  from ~3 nested paths with 40+ statements each to a single linear flow.

### 3.7 Small value objects
* `SocialSecurity` groups the three contributions that always travel together and knows its own
  `total()` (this sum was duplicated in both branches).
* `TaxCalculationResult` is an immutable data carrier, separating *computing* from *displaying*.

### 3.8 Error handling
`catch (Exception ex)` swallowed every possible exception. It now catches only the ones that can
actually occur (`IOException`, `NumberFormatException`, `StringIndexOutOfBoundsException` for
empty input). The contract code is also accepted in lowercase (`e`/`c`).

### 3.9 Rounding
`Double.parseDouble(df.format(x))` (format to text and parse back) was replaced by
`Math.round(x)`, which is clearer and does not depend on the system locale (with a Polish locale
`DecimalFormat` writes a comma, and the original parse would crash).

### 3.10 Formatting and project layout
Consistent 4-space indentation, one statement per line, no broken string concatenations across
lines, standard Maven-style `src/main/java` layout, packages by responsibility
(`contract`, `report`).

---

## 4. Verifying that behaviour is preserved

Since the original does not compile, the reference values were computed by hand from the
business rules in section 1 and checked against the program output.

**Income 5000, employment contract (E):**

| Item | Value |
|---|---|
| Social security (488.00 + 75.00 + 122.50) | 685.50 |
| Tax basis | 4314.50 |
| Health insurance 9% / 7.75% | 388.31 / 334.37 |
| Deductible expenses | 111.25 |
| Taxable income (rounded) | 4203 |
| Advance tax 18% | 756.54 |
| Advance tax to pay (rounded) | 376 |
| **Net income** | **3550.20** |

**Income 5000, civil contract (C):** deductible expenses 862.90, taxable income 3452,
advance tax to pay 287, **net income 3639.20**.

---

## 5. How to run

```bash
javac -d out $(find src -name "*.java")
java -cp out com.bartoszwalter.students.taxes.TaxCalculatorApp
```
