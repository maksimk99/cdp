package com.epam.cdp.maksim.katuranau.module5.config;

import com.epam.cdp.maksim.katuranau.module5.model.Employee;
import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;
import com.epam.cdp.maksim.katuranau.module5.spel.CustomSpelExpressionParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public abstract class GeneralConfiguration {

    @Bean
    public BigDecimal dollarCourse(CustomSpelExpressionParser parser) {
        return parser.parseExpression("1.2:2.6").getValue(BigDecimal.class);
    }

    @Bean("employeeKira")
    public Employee getEmployeeKira() {
        return new Employee("Kira", 22);
    }

    @Bean("employeeAlex")
    public Employee getEmployeeAlex() {
        return new Employee("Alex", 23);
    }

    @Bean
    public List<Position> getListPositions() {
        return new ArrayList<Position>() {{
            add(new Position("developer"));
            add(new Position("manager"));
            add(new Position("HR"));
        }};
    }

    @Bean
    public Map<Position, List<Employee>> getMapEmployeeOnPosition(List<Position> positionList) {
        return new HashMap<Position, List<Employee>>() {{
            put(positionList.get(0), Arrays.asList(new Employee("Petya", 23),
                    new Employee("Andrei", 22)));
            put(positionList.get(1), Collections.singletonList(new Employee("Sasha", 24)));
            put(positionList.get(2), Collections.singletonList(new Employee("Katya", 21)));
        }};
    }

    @Bean
    public Map<Position, Salary> getMapSalaryOnPosition(List<Position> positionList) {
        return new HashMap<Position, Salary>() {{
            put(positionList.get(0), new Salary(BigDecimal.valueOf(1200)));
            put(positionList.get(1), new Salary(BigDecimal.valueOf(1500)));
            put(positionList.get(2), new Salary(BigDecimal.valueOf(1100)));
        }};
    }
}
