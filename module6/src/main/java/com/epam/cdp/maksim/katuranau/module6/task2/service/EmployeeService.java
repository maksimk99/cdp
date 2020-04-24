package com.epam.cdp.maksim.katuranau.module6.task2.service;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Employee;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Position;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void setEmployeeForPosition(Map<Position, List<Employee>> employeeForPosition);

    void hireEmployeeForPosition(Employee employee, Position position);

    void fireEmployeesForPosition(Position position);

    void addPosition(Position position);

    List<Employee> getEmployeesForPosition(Position position);
}
