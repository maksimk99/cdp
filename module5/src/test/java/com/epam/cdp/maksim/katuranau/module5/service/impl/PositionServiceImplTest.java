package com.epam.cdp.maksim.katuranau.module5.service.impl;

import com.epam.cdp.maksim.katuranau.module5.model.Position;
import com.epam.cdp.maksim.katuranau.module5.model.Salary;
import com.epam.cdp.maksim.katuranau.module5.service.PositionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class PositionServiceImplTest extends BaseTest {

    @Autowired
    private PositionService positionService;
    private Salary salary;

    @Before
    public void setUp() {
        salary = new Salary(BigDecimal.TEN);
    }

    @Test
    public void getAvailablePositionsList() {
        assertTrue(positionService.getAvailablePositionsList().size() > 0);
    }

    @Test
    public void getPositionById() {
        assertEquals("manager", positionService.getPositionById(1).getPositionName());
    }

    @Test
    public void getNotExistPositionById() {
        assertNull(positionService.getPositionById(-10));
    }

    @Test
    public void addPosition() {
        Position position = new Position("NewPosition");
        positionService.addPosition(position, salary);
        assertTrue((positionService.getAvailablePositionsList().contains(position)));
    }

    @Test
    public void addPositionValidationError() {
        Position position = new Position("");
        positionService.addPosition(position, salary);
        assertFalse((positionService.getAvailablePositionsList().contains(position)));
    }

    @Test
    public void updatePosition() {
        Position newPosition = new Position("NewPosition");
        Position updatedPosition = new Position("UpdatedPosition");
        int positionListSize = positionService.getAvailablePositionsList().size();
        positionService.addPosition(newPosition, salary);
        positionService.updatePosition(updatedPosition, positionListSize);
        assertEquals(updatedPosition.getPositionName(),
                positionService.getPositionById(positionListSize).getPositionName());
    }

    @Test
    public void updatePositionValidationError() {
        Position oldPosition = positionService.getPositionById(1);
        Position newPosition = new Position("");
        positionService.updatePosition(newPosition, 1);
        assertEquals(oldPosition.getPositionName(), positionService.getPositionById(1).getPositionName());
    }

    @Test
    public void deletePosition() {
        Position position = new Position("Position");
        positionService.addPosition(position, salary);
        int positionListSize = positionService.getAvailablePositionsList().size();
        positionService.deletePosition(positionListSize - 1);
        assertFalse(positionService.getAvailablePositionsList().contains(position));
        assertEquals(positionListSize - 1, positionService.getAvailablePositionsList().size());
    }
}
