package de.codehat.levelbuilder;

import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import java.io.File;

/**
 * Represents the model of the level builder.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class Model {

    /**
     * An array holding all level tiles.
     * Position in array equals position in level.
     */
    private Tile[][] tiles;

    /**
     * Holds the last save path.
     */
    public File lastSavePath;

    /**
     * Holds the last load path.
     */
    public File lastLoadPath;

    /**
     * Creates a new model.
     *
     * @param rows amount of rows.
     * @param cols amount of columns.
     */
    public Model(int rows, int cols) {
        this.tiles = new Tile[rows][cols];

        fillAllTilesWith(TileType.TERRAIN);
    }

    /**
     * Fills the whole array of level tiles with the given type.
     *
     * @param type the type to fill the tiles array with.
     */
    public void fillAllTilesWith(TileType type) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                tiles[row][col] = new Tile(row, col, type);
            }
        }
    }

    /**
     * Returns the array containing all current level tiles.
     *
     * @return the array of level tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Sets the array containing all current level tiles.
     *
     * @param tiles the new array of level tiles
     */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
