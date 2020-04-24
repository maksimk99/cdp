package com.epam.cdp.maksim.katuranau.module6.task2.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Salary {
    @NotNull(message = "Salary amount can't be null")
    private BigDecimal salaryAmount;

    public Salary() {
        this.salaryAmount = BigDecimal.ZERO;
    }

    public Salary(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public BigDecimal getSalaryAmount() {
        return salaryAmount;
    }

    public Salary setSalaryAmount(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
        return this;
    }

    public void increaseSalaryAmount(double percentage) {
        salaryAmount = salaryAmount.add(salaryAmount.multiply(BigDecimal.valueOf(percentage)));
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryAmount=" + salaryAmount +
                '}';
    }
}
