package de.codehat.levelbuilder.model;

import com.google.gson.annotations.Expose;

/**
 * Represents the position of a tile in a level.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class Position {

    /**
     * Thrown if the row or col coordinate are invalid (e.g. negative).
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
     * row-coordinate.
     */
    private int row;

    /**
     * col-coordinate.
     */
    private int col;

    /**
     * Creates a new position with two coordinates.
     *
     * @param row the row-coordinate
     * @param col the col-coordinate
     */
    public Position(final int row, final int col) {
        if (row < 0 || col < 0) {
            throw new PositionInvalidException();
        }

        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row-coordinate.
     *
     * @return the row-coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row-coordinate.
     *
     * @param row the row-coordinate
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * Returns the col-coordinate.
     *
     * @return the col-coordinate
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the col-coordinate.
     *
     * @param col the col-coordinate
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
        return String.format("{ Row: %d, Col: %d }", row, col);
    }
}
