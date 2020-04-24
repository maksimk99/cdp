package com.epam.cdp.maksim.katuranau.module6.task2.config;

import com.epam.cdp.maksim.katuranau.module6.task2.factory.EmployeeFactory;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Employee;
import com.epam.cdp.maksim.katuranau.module6.task2.service.impl.RandomNumberParserService;
import com.epam.cdp.maksim.katuranau.module6.task2.validator.JSRValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ComponentScan("com.epam.cdp.maksim.katuranau.module6.task2")
public class AnnotationConfig {

    @Bean("employeeKira")
    public Employee getEmployeeKira(@Qualifier("employeeFactoryKira") EmployeeFactory employeeFactory) {
        return employeeFactory.getObject();
    }

    @Bean("employeeAlex")
    public Employee getEmployeeAlex(@Qualifier("employeeFactoryAlex") EmployeeFactory employeeFactory) {
        return employeeFactory.getObject();
    }

    @Bean
    public JSRValidator getJSRValidator() {
        return JSRValidator.createJSRValidator();
    }

    @Bean
    public BigDecimal dollarCourse(RandomNumberParserService parserService) {
        return parserService.doParse("1.2:2.6", "randomNumberFromTwoValuesParser")
                .getValue(BigDecimal.class);
    }
}
