package com.epam.cdp.maksim.katuranau.module5.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Salary {
    @NotNull(message = "Salary amount can't be null")
    private BigDecimal salaryAmount;

    public Salary() {
    }

    public Salary(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public BigDecimal getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryAmount=" + salaryAmount +
                '}';
    }
}
