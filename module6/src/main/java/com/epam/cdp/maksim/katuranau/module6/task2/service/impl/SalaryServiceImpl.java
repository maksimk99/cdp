package com.epam.cdp.maksim.katuranau.module6.task2.service.impl;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Position;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Salary;
import com.epam.cdp.maksim.katuranau.module6.task2.service.SalaryService;
import com.epam.cdp.maksim.katuranau.module6.task2.validator.JSRValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class SalaryServiceImpl implements SalaryService {

    private static final Logger LOGGER = LogManager.getLogger(SalaryServiceImpl.class);

    private JSRValidator validator;
    private Map<Position, Salary> salaryOnPosition;
    private BigDecimal dollarCourse;

    @Autowired
    public SalaryServiceImpl(JSRValidator validator, BigDecimal dollarCourse) {
        this.validator = validator;
        this.dollarCourse = dollarCourse;
        this.salaryOnPosition = new HashMap<>();
    }

    @Autowired
    public void setSalaryOnPosition(Map<Position, Salary> salaryOnPosition) {
        this.salaryOnPosition = salaryOnPosition;
    }

    @Override
    public BigDecimal getDollarCourse() {
        return dollarCourse;
    }

    @Override
    public void setDollarCourse(BigDecimal dollarCourse) {
        if (validator.isValidValue(dollarCourse)) {
            salaryOnPosition.forEach((position, salary) -> salary.setSalaryAmount(
                    salary.getSalaryAmount().multiply(this.dollarCourse.divide(dollarCourse, 0))));
            this.dollarCourse = dollarCourse;
            LOGGER.debug("DollarCourse was set to {}", dollarCourse);
        } else {
            LOGGER.error("dollar course has validation error");
        }
    }

    @Override
    public Salary getSalaryOnPosition(Position position) {
        if (validator.isValid(position, Position.class)) {
            LOGGER.debug("Method getEmployeesForPosition ({}) was invoked", position.getPositionName());
            return salaryOnPosition.get(position);
        } else {
            LOGGER.error("There is validation error ({})", position);
            return null;
        }

    }

    @Override
    public void addSalaryOnPosition(Position position, Salary salary) {
        if (validator.isValid(position, Position.class) && validator.isValid(salary, Salary.class)
                && validator.isValidValue(salary.getSalaryAmount())) {
            salary.setSalaryAmount(salary.getSalaryAmount().multiply(dollarCourse));
            salaryOnPosition.put(position, salary);
            LOGGER.debug("Salary ({}) was set for position ({})", salary, position.getPositionName());
        } else {
            LOGGER.error("There is validation error ({}), ({})", position, salary);
        }
    }

    @Override
    public void increaseSalaryPercentage(double percentage) {
        if (validator.isValidValue(percentage)) {
            salaryOnPosition.forEach((position, salary) -> salary.setSalaryAmount(
                    salary.getSalaryAmount().add(salary.getSalaryAmount().multiply(BigDecimal.valueOf(percentage)))
            ));
            LOGGER.debug("Salary was increased on ({})%", percentage);
        }
    }

    @Override
    public void decreaseSalaryPercentage(double percentage) {
        if (validator.isValidValue(percentage)) {
            salaryOnPosition.forEach((position, salary) -> salary.setSalaryAmount(
                    salary.getSalaryAmount().subtract(salary.getSalaryAmount().multiply(BigDecimal.valueOf(percentage)))
            ));
            LOGGER.debug("Salary was decreased on ({})%", percentage);
        }
    }

    @Override
    public void deleteSalaryOnPosition(Position position) {
        if (salaryOnPosition.containsKey(position)) {
            salaryOnPosition.remove(position);
            LOGGER.debug("Position ({}) was deleted", position.getPositionName());
        } else {
            LOGGER.error("There is no " + position);
        }
    }
}
