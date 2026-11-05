package com.bartoszwalter.students.taxes.contract;

import java.util.Optional;

/** Single place where a new contract type has to be registered. */
public final class ContractFactory {

    private ContractFactory() {
    }

    public static Optional<Contract> fromCode(char code, double grossIncome) {
        switch (Character.toUpperCase(code)) {
            case 'E':
                return Optional.of(new EmploymentContract(grossIncome));
            case 'C':
                return Optional.of(new CivilContract(grossIncome));
            default:
                return Optional.empty();
        }
    }
}
