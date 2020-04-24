package com.epam.cdp.maksim.katuranau.module6.task2.service.impl;

import com.epam.cdp.maksim.katuranau.module6.task2.model.Position;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Salary;
import com.epam.cdp.maksim.katuranau.module6.task2.model.Skill;
import com.epam.cdp.maksim.katuranau.module6.task2.service.EmployeeService;
import com.epam.cdp.maksim.katuranau.module6.task2.service.PositionService;
import com.epam.cdp.maksim.katuranau.module6.task2.service.SalaryService;
import com.epam.cdp.maksim.katuranau.module6.task2.validator.JSRValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private static final Logger LOGGER = LogManager.getLogger(PositionServiceImpl.class);

    private JSRValidator validator;
    private List<Position> positionList;
    private EmployeeService employeeService;
    private SalaryService salaryService;

    @Autowired
    public PositionServiceImpl(JSRValidator validator, EmployeeService employeeService, SalaryService salaryService) {
        this.validator = validator;
        this.employeeService = employeeService;
        this.salaryService = salaryService;
        this.positionList = new ArrayList<>();
    }

    @Override
    public List<Position> getAvailablePositionsList() {
        return positionList;
    }

    @Override
    public Position getPositionById(int positionId) {
        if (positionId >= 0 && positionId < positionList.size()) {
            return positionList.get(positionId);
        } else {
            LOGGER.error("There is no position with id = {}", positionId);
            return null;
        }
    }

    @Override
    public void addPosition(Position position, Salary salary) {
        if (validator.isValid(position, Position.class) && validator.isValid(salary, Salary.class)
                && validator.isValidValue(salary.getSalaryAmount())) {
            positionList.add(position);
            employeeService.addPosition(position);
            salaryService.addSalaryOnPosition(position, salary);
            LOGGER.debug("New position ({}) was added", position);
        } else {
            LOGGER.error("There is validation error ({}), ({})", position, salary);
        }
    }

    @Autowired
    @Override
    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    public void updatePosition(Position position, int positionId) {
        if (validator.isValid(position, Position.class)) {
            if (positionId >= 0 && positionId < positionList.size()) {
                Position updatedPosition = positionList.get(positionId);
                updatedPosition.setPositionName(position.getPositionName());
                LOGGER.debug("Position ({}) was updated", position);
            } else {
                LOGGER.error("There is no position with id = {}", positionId);
            }
        } else {
            LOGGER.error("There is validation error ({})", position);
        }
    }

    @Override
    public void deletePosition(int positionId) {
        if (positionId >= 0 && positionId < positionList.size()) {
            Position position = positionList.get(positionId);
            positionList.remove(position);
            employeeService.fireEmployeesForPosition(position);
            salaryService.deleteSalaryOnPosition(position);
            LOGGER.debug("Position ({}) was deleted", position);
        } else {
            LOGGER.error("There is no position with id = {}", positionId);
        }
    }

    @Override
    public void deleteSkill(int positionId, int skillId) {
        if (positionId >= 0 && positionId < positionList.size()) {
            Position position = positionList.get(positionId);
            if (skillId >= 0 && skillId < position.getSkillList().size()) {
                Skill skill = position.getSkillList().remove(skillId);
                Salary salary = salaryService.getSalaryOnPosition(position);
                salary.setSalaryAmount(
                        salary.getSalaryAmount().subtract(salary.getSalaryAmount()
                                .multiply(BigDecimal.valueOf(skill.getPriority() * 0.01))));
            }
        } else {
            LOGGER.error("There is no position with id = {}", positionId);
        }
    }

    @Override
    public void printEmployeesForPosition(int positionId) {
        if (positionId >= 0 && positionId < positionList.size()) {
            Position position = positionList.get(positionId);
            System.out.println(position + " " + salaryService.getSalaryOnPosition(position));
            employeeService.getEmployeesForPosition(position).forEach(System.out::println);
        } else {
            LOGGER.error("There is no position with id = {}", positionId);
        }
    }
}
