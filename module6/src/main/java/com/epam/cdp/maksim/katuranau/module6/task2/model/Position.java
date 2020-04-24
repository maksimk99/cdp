package com.epam.cdp.maksim.katuranau.module6.task2.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class Position {
    @NotBlank(message = "Position name can't be null or empty")
    private String positionName;
    @Min(value = 0, message = "Age can't be negative")
    private int numberOfEmployees;
    @NotNull
    private List<@Valid Skill> skillList;

    public Position(String positionName, int numberOfEmployees, List<Skill> skillList) {
        this.positionName = positionName;
        this.numberOfEmployees = numberOfEmployees;
        this.skillList = skillList;
    }

    public Position(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return numberOfEmployees == position.numberOfEmployees &&
                positionName.equals(position.positionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionName);
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionName='" + positionName + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }
}
