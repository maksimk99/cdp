package com.epam.cdp.maksim.katuranau.module6.task2.model;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Employee {
    @NotBlank
    private String name;
    @Min(value = 0, message = "Age can't be negative")
    @Max(value = 100, message = "Age can't be more than 100 years")
    private int age;
    @NotNull
    private List<@Valid Skill> skillList;

    public Employee() {
    }

    public Employee(String name, int age, List<Skill> skillList) {
        this.name = name;
        this.age = age;
        this.skillList = skillList;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
