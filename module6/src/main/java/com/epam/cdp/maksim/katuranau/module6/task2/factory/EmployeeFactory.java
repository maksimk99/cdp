package com.epam.cdp.maksim.katuranau.module6.task2.factory;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Employee;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Skill;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;

public class EmployeeFactory implements FactoryBean<Employee> {

    private String name;
    private int age;
    private List<Skill> skillList;

    public EmployeeFactory(String name, int age, List<Skill> skillList) {
        this.name = name;
        this.age = age;
        this.skillList = skillList;
    }

    @Override
    public Employee getObject() {
        return new Employee(name, age, skillList);
    }

    @Override
    public Class<?> getObjectType() {
        return Employee.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
