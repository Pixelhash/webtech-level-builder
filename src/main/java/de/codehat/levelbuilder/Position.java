package de.codehat.levelbuilder;

import com.google.gson.annotations.Expose;

/**
 * Represents the position of a tile in a level.
 *
 * @author Marc-Niclas H. (codehat)
 */
class Position {

    /**
     * x-coordinate
     */
    @Expose
    private int row;

    /**
     * y-coordinate
     */
    @Expose
    private int col;

    /**
     * Creates a new position with two coordinates.
     *
     * @param row as x-coordinate
     * @param col as y-coordinate
     */
    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
