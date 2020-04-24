package com.epam.cdp.maksim.katuranau.module6.task2.config;

import com.epam.cdp.maksim.katuranau.module6.task2.factory.EmployeeFactory;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Skill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class FactoryConfig {

    @Bean("employeeFactoryKira")
    public EmployeeFactory employeeFactoryKira(List<Skill> skillList) {
        return new EmployeeFactory("Kira", 22,
                Arrays.asList(skillList.get(1), skillList.get(2), skillList.get(3)));
    }

    @Bean("employeeFactoryAlex")
    public EmployeeFactory employeeFactoryAlex(List<Skill> skillList) {
        return new EmployeeFactory("Alex", 23,
                Arrays.asList(skillList.get(0), skillList.get(1), skillList.get(2)));
    }
}
