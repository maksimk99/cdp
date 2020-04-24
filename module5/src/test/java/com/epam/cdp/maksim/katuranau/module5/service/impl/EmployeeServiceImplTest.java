package com.epam.cdp.maksim.katuranau.module5.service.impl;

import com.epam.cdp.maksim.katuranau.module5.model.Employee;
import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmployeeServiceImplTest extends BaseTest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ApplicationContext context;
    private Employee employee;
    private Position position;

    @Before
    public void setUp() {
        employee = new Employee("Andrei", 25);
        position = context.getBean("positionDeveloper", Position.class);
    }

    @Test
    public void hireEmployeeForPosition() {
        employeeService.hireEmployeeForPosition(employee, position);
        assertTrue(employeeService.getEmployeesForPosition(position).contains(employee));
    }

    @Test
    public void hireEmployeeForPositionValidationError() {
        employee.setAge(-15);
        employeeService.hireEmployeeForPosition(employee, position);
        assertFalse(employeeService.getEmployeesForPosition(position).contains(employee));
    }

    @Test
    public void fireEmployeesForPosition() {
        Position position = context.getBean("positionManager", Position.class);
        assertTrue(employeeService.getEmployeesForPosition(position).size() > 0);
        employeeService.fireEmployeesForPosition(position);
        assertNull(employeeService.getEmployeesForPosition(position));
    }

    @Test
    public void addPosition() {
        Position position = new Position("guard");
        assertNull(employeeService.getEmployeesForPosition(position));
        employeeService.addPosition(position);
        assertNotNull(employeeService.getEmployeesForPosition(position));
    }

    @Test
    public void addPositionValidationError() {
        Position position = new Position("");
        assertNull(employeeService.getEmployeesForPosition(position));
        employeeService.addPosition(position);
        assertNull(employeeService.getEmployeesForPosition(position));
    }

    @Test
    public void getEmployeesForPosition() {
        assertTrue(employeeService.getEmployeesForPosition(position).size() > 0);
    }

    @Test
    public void getEmployeesForNotExistPosition() {
        Position position = new Position("NotExistPosition");
        assertNull(employeeService.getEmployeesForPosition(position));
    }

    @Test
    public void getEmployeesForPositionValidationError() {
        Position position = new Position(null);
        assertNull(employeeService.getEmployeesForPosition(position));
    }
}
