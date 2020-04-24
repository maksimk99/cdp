package com.epam.cdp.maksim.katuranau.module5.service.impl;

import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;
import com.epam.cdp.maksim.katuranau.module5.service.EmployeeService;
import com.epam.cdp.maksim.katuranau.module5.service.SalaryService;
import com.epam.cdp.maksim.katuranau.module5.validator.JSRValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PositionServiceImplMockitoTest {

    @Mock
    private JSRValidator validator;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private SalaryService salaryService;
    @InjectMocks
    private PositionServiceImpl positionService;
    private List<Position> positionList;
    private Salary salary;

    @Before
    public void setup() {
        salary = new Salary(BigDecimal.TEN);
        positionList = new ArrayList<Position>() {{
            add(new Position("positionDeveloper"));
            add(new Position("positionManager"));
            add(new Position("positionHR"));
        }};
        positionService.setPositionList(positionList);
    }

    @Test
    public void getAvailablePositionsList() {
        assertEquals(positionList.size(), positionService.getAvailablePositionsList().size());
    }

    @Test
    public void getPositionById() {
        assertEquals(positionList.get(1), positionService.getPositionById(1));
    }

    @Test
    public void addPosition() {
        Position position = new Position("NewPosition");
        when(validator.isValid(position, Position.class)).thenReturn(true);
        when(validator.isValid(salary, Salary.class)).thenReturn(true);
        when(validator.isValidValue(salary.getSalaryAmount())).thenReturn(true);
        int listPositionSize = positionService.getAvailablePositionsList().size();
        positionService.addPosition(position, salary);
        assertEquals(position, positionService.getPositionById(listPositionSize));
    }

    @Test
    public void updatePosition() {
        Position position = new Position("NewPosition");
        when(validator.isValid(position, Position.class)).thenReturn(true);
        positionService.updatePosition(position, 1);
        assertEquals(position, positionService.getPositionById(1));
    }

    @Test
    public void deletePosition() {
        int listPositionSize = positionService.getAvailablePositionsList().size();
        positionService.deletePosition(1);
        assertEquals(listPositionSize - 1, positionService.getAvailablePositionsList().size());
    }
}
