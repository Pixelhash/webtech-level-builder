package de.codehat.levelbuilder.model;

import com.google.gson.annotations.Expose;

/**
 * Represents the position of a tile in a level.
 *
 * @author Marc-Niclas H. (codehat)
 */
class Position {

    /**
     * X-coordinate.
     */
    @Expose
    private int row;

    /**
     * Y-coordinate.
     */
    @Expose
    private int col;

    /**
     * Creates a new position with two coordinates.
     *
     * @param row as x-coordinate
     * @param col as y-coordinate
     */
    Position(final int row, final int col) {
        this.row = row;
        this.col = col;
    }
}
