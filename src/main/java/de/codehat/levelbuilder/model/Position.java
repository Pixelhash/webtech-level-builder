package de.codehat.levelbuilder.model;

import com.google.gson.annotations.Expose;

/**
 * Represents the position of a tile in a level.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class Position {

    /**
     * Thrown if the x or y coordinate are invalid (e.g. negative).
     *
     * @author Marc-Niclas H. (codehat)
     */
    public class PositionInvalidException extends RuntimeException {

        /**
         * Creates a new {@link PositionInvalidException}.
         */
        public PositionInvalidException() {
            super("One or both coordinates of the position object are invalid!");
        }
    }

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
    public Position(final int row, final int col) {
        if (row < 0 || col < 0) {
            throw new PositionInvalidException();
        }

        this.row = row;
        this.col = col;
    }

    /**
     * Returns the x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the x-coordinate.
     *
     * @param row the x-coordinate
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * Returns the y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the y-coordinate.
     *
     * @param col the y-coordinate
     */
    public void setCol(final int col) {
        this.col = col;
    }

    @Override
    public boolean equals(final Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Position p = (Position) o;
        return row == p.getRow() && col == p.getCol();
    }

    @Override
    public String toString() {
        return String.format("{ X: %d, Y: %d }", row, col);
    }
}
