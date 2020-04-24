package com.epam.cdp.maksim.katuranau.module6.task2.config;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Employee;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Position;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Salary;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Skill;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Configuration
public class MapAndListsConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    @Scope("prototype")
    public Salary getSalary() {
        return new Salary(BigDecimal.valueOf(1000 + new Random().nextInt(1000)));
    }

    @Bean
    public List<Skill> skillList() {
        return new ArrayList<Skill>() {{
            add(new Skill("englishB1", 2));
            add(new Skill("higher education", 4));
            add(new Skill("sociability", 3));
            add(new Skill("adulthood", 5));
            add(new Skill("has car", 2));
        }};
    }

    @Bean
    public List<Position> getListPositions(List<Skill> skillList) {
        return new ArrayList<Position>() {{
            add(new Position("developer", 2,
                    new ArrayList<>(Arrays.asList(skillList.get(0), skillList.get(3)))));
            add(new Position("manager", 1,
                    new ArrayList<>(Arrays.asList(skillList.get(1), skillList.get(2)))));
            add(new Position("HR", 1,
                    new ArrayList<>(Arrays.asList(skillList.get(3), skillList.get(4)))));
        }};
    }

    @Bean
    public Map<Position, List<Employee>> getMapEmployeeOnPosition(List<Position> positionList, List<Skill> skillList) {
        return new HashMap<Position, List<Employee>>() {{
            put(positionList.get(0), new ArrayList<>(Arrays.asList(
                    new Employee("Petya", 23, Arrays.asList(skillList.get(0), skillList.get(3))),
                    new Employee("Andrei", 22, Arrays.asList(skillList.get(0), skillList.get(3),
                            skillList.get(4))))));
            put(positionList.get(1), Collections.singletonList(new Employee("Sasha", 24,
                    new ArrayList<>(Arrays.asList(skillList.get(1), skillList.get(2), skillList.get(3))))));
            put(positionList.get(2), Collections.singletonList(new Employee("Katya", 21,
                    new ArrayList<>(Arrays.asList(skillList.get(0), skillList.get(3), skillList.get(4))))));
        }};
    }

    @Bean
    public Map<Position, Salary> getMapSalaryOnPosition(List<Position> positionList) {
        return new HashMap<Position, Salary>() {{
            for (Position position : positionList) {
                Salary salary = applicationContext.getBean(Salary.class);
                salary.increaseSalaryAmount(Math.random() * position.getSkillList().size() * 0.1);
                put(position, salary);
            }
        }};
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}
