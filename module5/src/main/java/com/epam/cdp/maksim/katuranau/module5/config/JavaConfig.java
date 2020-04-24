package com.epam.cdp.maksim.katuranau.module5.config;

import com.epam.cdp.maksim.katuranau.module5.model.Employee;
import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;
import com.epam.cdp.maksim.katuranau.module5.service.EmployeeService;
import com.epam.cdp.maksim.katuranau.module5.service.PositionService;
import com.epam.cdp.maksim.katuranau.module5.service.SalaryService;
import com.epam.cdp.maksim.katuranau.module5.service.impl.EmployeeServiceImpl;
import com.epam.cdp.maksim.katuranau.module5.service.impl.PositionServiceImpl;
import com.epam.cdp.maksim.katuranau.module5.service.impl.SalaryServiceImpl;
import com.epam.cdp.maksim.katuranau.module5.spel.CustomSpelExpressionParser;
import com.epam.cdp.maksim.katuranau.module5.validator.JSRValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Configuration
@Import(GeneralConfiguration.class)
public class JavaConfig {

    @Bean
    public CustomSpelExpressionParser getCustomSpelExpressionParser() {
        return new CustomSpelExpressionParser();
    }

    @Bean
    public JSRValidator getJSRValidator() {
        return new JSRValidator();
    }

    @Bean
    public EmployeeService getEmployeeService(JSRValidator validator,
                                              Map<Position, List<Employee>> employeeForPosition) {
        EmployeeService employeeService = new EmployeeServiceImpl(validator);
        employeeService.setEmployeeForPosition(employeeForPosition);
        return employeeService;
    }

    @Bean
    public SalaryService getSalaryService(JSRValidator validator, BigDecimal dollarCourse,
                                          Map<Position, Salary> salaryOnPosition) {
        SalaryService salaryService = new SalaryServiceImpl(validator, dollarCourse);
        salaryService.setSalaryOnPosition(salaryOnPosition);
        return salaryService;
    }

    @Bean
    public PositionService getPositionService(JSRValidator validator, EmployeeService employeeService,
                                              SalaryService salaryService, List<Position> positionList) {
        PositionService positionService = new PositionServiceImpl(validator, employeeService, salaryService);
        positionService.setPositionList(positionList);
        return positionService;
    }
}
