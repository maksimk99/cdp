package com.epam.cdp.maksim.katuranau.module5.runner;

import com.epam.cdp.maksim.katuranau.module5.config.AnnotationConfig;
import com.epam.cdp.maksim.katuranau.module5.config.JavaConfig;
import com.epam.cdp.maksim.katuranau.module5.model.Employee;
import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;
import com.epam.cdp.maksim.katuranau.module5.service.EmployeeService;
import com.epam.cdp.maksim.katuranau.module5.service.PositionService;
import com.epam.cdp.maksim.katuranau.module5.service.SalaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task with xml configuration:");
        executeTask(new ClassPathXmlApplicationContext("context.xml"));
        System.out.println("\n\nTask with annotation configuration:");
        executeTask(new AnnotationConfigApplicationContext(AnnotationConfig.class));
        System.out.println("\n\nTask with java configuration:");
        executeTask(new AnnotationConfigApplicationContext(JavaConfig.class));
    }

    private static void executeTask(ApplicationContext context) {
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
    }
}
