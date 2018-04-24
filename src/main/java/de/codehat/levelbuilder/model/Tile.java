package de.codehat.levelbuilder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.swing.JButton;

/**
 * Represents a tile in the level, which has a {@link TileType}.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class Tile {

    /**
     * The position of the tile in the level.
     */
    @Expose
    private Position position;

    /**
     * The button of this tile in the GUI.
     */
    private JButton button;

    /**
     * The type of this tile.
     */
    @Expose
    @SerializedName("type")
    private TileType tileType;

    /**
     * Creates a new tile.
     *
     * @param x x-coordinate of this tile
     * @param y y-coordinate of this tile
     * @param button the button of this tile in the GUI
     * @param tileType the type of this tile
     */
    public Tile(final int x, final int y, final JButton button,
         final TileType tileType) {
        this.position = new Position(x, y);
        this.button = button;
        this.tileType = tileType;
    }

    /**
     * Returns the tile's position.
     *
     * @return the tile's position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the tile's position.
     *
     * @param position the tile's position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * Returns the tile's button in the GUI.
     *
     * @return the tile's button in the GUI
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Sets the tile's button in the GUI.
     *
     * @param button the tile's button in the GUI to set
     */
    public void setButton(final JButton button) {
        this.button = button;
    }

    /**
     * Returns the tile type.
     *
     * @return the tile type
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     * Sets the tile type.
     *
     * @param tileType the tile type to set.
     */
    public void setTileType(final TileType tileType) {
        this.tileType = tileType;
    }
}