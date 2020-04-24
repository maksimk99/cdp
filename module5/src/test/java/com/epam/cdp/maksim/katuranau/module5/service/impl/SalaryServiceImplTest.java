package com.epam.cdp.maksim.katuranau.module5.service.impl;

import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;
import com.epam.cdp.maksim.katuranau.module5.service.SalaryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertNull;

public class SalaryServiceImplTest extends BaseTest {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private ApplicationContext context;
    private Position position;

    @Before
    public void setUp() {
        position = context.getBean("positionDeveloper", Position.class);
    }

    @Test
    public void setDollarCourse() {
        BigDecimal newDollarCourse = BigDecimal.valueOf(2.034);
        salaryService.setDollarCourse(newDollarCourse);
        assertEquals(newDollarCourse, salaryService.getDollarCourse());
    }

    @Test
    public void getSalaryOnPosition() {
        assertNotNull(salaryService.getSalaryOnPosition(position));
    }

    @Test
    public void getSalaryOnPositionValidationError() {
        Position position = new Position("");
        assertNull(salaryService.getSalaryOnPosition(position));
    }

    @Test
    public void addSalaryOnPosition() {
        Position position = new Position("NewPosition");
        Salary salary = new Salary(BigDecimal.TEN);
        salaryService.addSalaryOnPosition(position, salary);
        assertEquals(salary, salaryService.getSalaryOnPosition(position));
    }

    @Test
    public void setSalaryOnPositionValidationError() {
        Position position = new Position(null);
        Salary salary = new Salary(BigDecimal.TEN);
        salaryService.addSalaryOnPosition(position, salary);
        assertNull(salaryService.getSalaryOnPosition(position));
    }

    @Test
    public void increaseSalaryPercentage() {
        BigDecimal newSalary = salaryService.getSalaryOnPosition(position).getSalaryAmount();
        newSalary = newSalary.add(newSalary.multiply(BigDecimal.valueOf(0.10)));
        salaryService.increaseSalaryPercentage(0.10);
        assertEquals(newSalary, salaryService.getSalaryOnPosition(position).getSalaryAmount());
    }

    @Test
    public void increaseSalaryPercentageValidationError() {
        BigDecimal oldSalary = salaryService.getSalaryOnPosition(position).getSalaryAmount();
        salaryService.increaseSalaryPercentage(-0.10);
        assertEquals(oldSalary, salaryService.getSalaryOnPosition(position).getSalaryAmount());
    }

    @Test
    public void decreaseSalaryPercentage() {
        BigDecimal newSalary = salaryService.getSalaryOnPosition(position).getSalaryAmount();
        newSalary = newSalary.subtract(newSalary.multiply(BigDecimal.valueOf(0.10)));
        salaryService.decreaseSalaryPercentage(0.10);
        assertEquals(newSalary, salaryService.getSalaryOnPosition(position).getSalaryAmount());
    }

    @Test
    public void decreaseSalaryPercentageValidationError() {
        BigDecimal oldSalary = salaryService.getSalaryOnPosition(position).getSalaryAmount();
        salaryService.decreaseSalaryPercentage(-0.10);
        assertEquals(oldSalary, salaryService.getSalaryOnPosition(position).getSalaryAmount());
    }

    @Test
    public void deleteSalaryOnPosition() {
        Position position = new Position("NewPosition");
        Salary salary = new Salary(BigDecimal.TEN);
        salaryService.addSalaryOnPosition(position, salary);
        assertNotNull(salaryService.getSalaryOnPosition(position));
        salaryService.deleteSalaryOnPosition(position);
        assertNull(salaryService.getSalaryOnPosition(position));
    }
}
