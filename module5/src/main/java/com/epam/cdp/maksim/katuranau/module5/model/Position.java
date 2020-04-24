package com.epam.cdp.maksim.katuranau.module5.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Position {
    @NotBlank(message = "Position name can't be null or empty")
    private String positionName;
    @Min(value = 0, message = "Age can't be negative")
    private int numberOfEmployees;

    public Position() {
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
