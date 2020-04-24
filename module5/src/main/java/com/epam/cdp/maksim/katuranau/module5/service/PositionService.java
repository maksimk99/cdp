package com.epam.cdp.maksim.katuranau.module5.service;

import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;

import java.util.List;

public interface PositionService {
    List<Position> getAvailablePositionsList();

    Position getPositionById(int positionId);

    void addPosition(Position position, Salary salary);

    void setPositionList(List<Position> positionList);

    void updatePosition(Position position, int positionId);

    void deletePosition(int positionId);

    void printEmployeesForPosition(int positionId);
}
