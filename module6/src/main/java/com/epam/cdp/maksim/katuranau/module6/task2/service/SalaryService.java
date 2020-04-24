package com.epam.cdp.maksim.katuranau.module6.task2.service;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Position;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Salary;

import java.math.BigDecimal;
import java.util.Map;

public interface SalaryService {
    void setSalaryOnPosition(Map<Position, Salary> salaryOnPosition);

    BigDecimal getDollarCourse();

    void setDollarCourse(BigDecimal dollarCourse);

    Salary getSalaryOnPosition(Position position);

    void addSalaryOnPosition(Position position, Salary salary);

    void increaseSalaryPercentage(double percentage);

    void decreaseSalaryPercentage(double percentage);

    void deleteSalaryOnPosition(Position position);
}
