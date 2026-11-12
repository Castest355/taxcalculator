# TaxCalculator App

**Contributors:** Alejandro Prada Martín, Sergio Álvarez Marcos.

In this assignment, the main objective was to refactor the TaxCalculator application by applying Clean Code principles, SOLID principles, and refactoring techniques.

## CHANGES

- Renamed variables and methods (`soc_health1` to `healthInsurance`, `df00` to `TWO_DECIMALS`, etc.) because the original names were unclear or incorrect.

- Moved the magic numbers to `TaxRates`, so each rate has a descriptive name and is defined in one place.

- Removed unnecessary comments, since some were incorrect and others only explained poorly named variables.

- Removed mutable public static fields because results depended on the order of method calls. Now calculation values are local or stored in immutable objects.

- Split the original application into separate classes for input handling, calculations, and printing, so each class has a specific responsibility.

- Created an abstract `Contract` class with two subclasses (Template Method) to remove duplicated conditional branches. New contract types can be added by creating a subclass and registering it in `ContractFactory`.

- Introduced `ContractFactory` (Simple Factory) to centralize contract creation.

- Added `SocialSecurity` and `TaxCalculationResult` to group related data and separate calculations from presentation.

- Replaced `catch (Exception)` with more specific exception handling to make errors easier to identify.

- Used `Math.round()` instead of formatting numbers as text and parsing them back, simplifying the code and avoiding locale-dependent parsing issues.

- Improved code formatting and organized the project using Maven-style folders.

- Used `private final` fields and immutable result objects to protect calculation data from unintended modifications.

- Applied Extract Class and Introduce Parameter Object to reduce the `TaxCalculationResult` constructor from 14 to 5 parameters by introducing `HealthInsuranceResult` and `IncomeTaxResult`.

- Applied Extract Method in `TaxReportPrinter` to divide the printing logic into smaller, more readable methods.

## STRUCTURAL DESIGN

Applied the **Single Responsibility Principle (SRP)** to separate user input, tax calculations, contract creation, and report presentation into different classes.

Two main design patterns were used in this assignment:

- **Template Method Pattern:** Defines the structure of an algorithm in a parent class, allowing subclasses to implement specific steps without modifying the general algorithm. Implemented in `Contract.java`.

- **Simple Factory Pattern:** Centralizes object creation, separating it from the main application logic. Implemented in `ContractFactory.java`.
