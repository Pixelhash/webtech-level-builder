package de.codehat.levelbuilder;

import de.codehat.levelbuilder.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    private Position position;

    @Before
    public void setUp() {
        position = new Position(5, 2);
    }

    @Test
    public void positionNotNull() {
        assertNotNull(position);
    }

    @Test
    public void positionValuesCorrect() {
        int x = 5;
        int y = 2;

        assertEquals(x, position.getRow());
        assertEquals(y, position.getCol());
    }

    @Test(expected = Position.PositionInvalidException.class)
    public void positionInvalidValue() {
        new Position(-1, 2);
    }
}
