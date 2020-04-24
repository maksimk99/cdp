package com.epam.cdp.maksim.katuranau.module5.service.impl;

import com.epam.cdp.maksim.katuranau.module5.model.Employee;
import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.service.EmployeeService;
import com.epam.cdp.maksim.katuranau.module5.validator.JSRValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

    private JSRValidator validator;
    private Map<Position, List<Employee>> employeeForPosition;

    @Autowired
    public EmployeeServiceImpl(JSRValidator validator) {
        this.validator = validator;
        this.employeeForPosition = new HashMap<>();
    }

    @Autowired
    @Override
    public void setEmployeeForPosition(Map<Position, List<Employee>> employeeForPosition) {
        this.employeeForPosition = employeeForPosition;
    }

    @Override
    public void hireEmployeeForPosition(Employee employee, Position position) {
        if (validator.isValid(employee, Employee.class) && validator.isValid(position, Position.class)) {
            if (employeeForPosition.containsKey(position)) {
                employeeForPosition.get(position).add(employee);
                position.setNumberOfEmployees(position.getNumberOfEmployees() + 1);
                LOGGER.debug("New employee was hired for position " + position.getPositionName());
            } else {
                LOGGER.error("There is no " + position);
            }
        } else {
            LOGGER.error("There is validation error ({}), ({})", position, employee);
        }
    }

    @Override
    public void fireEmployeesForPosition(Position position) {
        if (validator.isValid(position, Position.class)) {
            if (employeeForPosition.containsKey(position)) {
                employeeForPosition.remove(position);
                LOGGER.debug("Employees on position " + position.getPositionName() + " were fired");
            } else {
                LOGGER.error("There is no " + position);
            }
        } else {
            LOGGER.error("There is validation error ({})", position);
        }
    }

    @Override
    public void addPosition(Position position) {
        if (validator.isValid(position, Position.class)) {
            employeeForPosition.put(position, new ArrayList<>());
            LOGGER.debug("Position " + position.getPositionName() + " was added");
        } else {
            LOGGER.error("There is validation error " + position);
        }
    }

    @Override
    public List<Employee> getEmployeesForPosition(Position position) {
        if (validator.isValid(position, Position.class)) {
            if (employeeForPosition.containsKey(position)) {
                LOGGER.debug("Method getEmployeesForPosition was invoked");
                return employeeForPosition.get(position);
            } else {
                LOGGER.error("There is no " + position);
                return null;
            }
        } else {
            LOGGER.error("There is validation error " + position);
            return null;
        }
    }
}
