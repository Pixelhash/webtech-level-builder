package de.codehat.levelbuilder;

import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {

    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(4, 2, null, TileType.TERRAIN);
    }

    @Test
    public void tileNotNull() {
        assertNotNull(tile);
    }

    @Test
    public void tilePositionNotNull() {
        assertNotNull(tile.getPosition());
    }

    @Test
    public void tilePositionCorrect() {
        int x = 4;
        int y = 2;

        assertEquals(x, tile.getPosition().getRow());
        assertEquals(y, tile.getPosition().getCol());
    }

    @Test
    public void tileTypeNotNull() {
        assertNotNull(tile.getTileType());
    }

    @Test
    public void tileTypeCorrect() {
        assertEquals(TileType.TERRAIN, tile.getTileType());
    }

    @Test
    public void tileButtonIsNull() {
        assertNull(tile.getButton());
    }

}
