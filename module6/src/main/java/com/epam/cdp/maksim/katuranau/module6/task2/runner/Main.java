package com.epam.cdp.maksim.katuranau.module6.task2.runner;

import com.epam.cdp.maksim.katuranau.module6.task2.config.AnnotationConfig;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Employee;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Position;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Salary;
import com.epam.cdp.maksim.katuranau.module6.task2.service.EmployeeService;
import com.epam.cdp.maksim.katuranau.module6.task2.service.PositionService;
import com.epam.cdp.maksim.katuranau.module6.task2.service.SalaryService;
import com.epam.cdp.maksim.katuranau.module6.task2.service.impl.RandomNumberParserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.expression.Expression;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Employee employeeKira = context.getBean("employeeKira", Employee.class);
        Employee employeeAlex = context.getBean("employeeAlex", Employee.class);

        EmployeeService employeeService = context.getBean(EmployeeService.class);
        SalaryService salaryService = context.getBean(SalaryService.class);
        PositionService positionService = context.getBean(PositionService.class);

        Position positionManager = positionService.getPositionById(1);
        Salary managerSalary = salaryService.getSalaryOnPosition(positionManager);

        System.out.println("\nPrint employees on position №1:");
        positionService.printEmployeesForPosition(1);
        Position position = positionService.getPositionById(1);
        positionService.deletePosition(1);
        System.out.println("\nTry to print employees on position №1 after deleting(should print next position):");
        positionService.printEmployeesForPosition(1);
        positionService.updatePosition(position, 1);

        positionService.addPosition(positionManager, managerSalary);

        employeeService.hireEmployeeForPosition(employeeKira, positionManager);
        employeeService.hireEmployeeForPosition(employeeAlex, positionManager);

        System.out.println("\nPrint new added position with employees:");
        positionService.printEmployeesForPosition(2);

        salaryService.increaseSalaryPercentage(0.10);

        System.out.println("\nPrint new added position with employees after increasing salary on 10%:");
        positionService.printEmployeesForPosition(2);

        System.out.println("Salary before deleting skill: "
                + salaryService.getSalaryOnPosition(positionService.getPositionById(0)).getSalaryAmount());
        positionService.deleteSkill(0, 1);
        System.out.println("Salary after deleting skill: "
                + salaryService.getSalaryOnPosition(positionService.getPositionById(0)).getSalaryAmount());

        RandomNumberParserService parserService = context.getBean(RandomNumberParserService.class);
        Expression expression = parserService.doParse("4", "randomNumberFromOneValueParser");
        System.out.println("Create random number that starts from 4: " + expression.getValue(Double.class));

        context.close();
    }
}
