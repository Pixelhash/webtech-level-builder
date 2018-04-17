package de.codehat.levelbuilder;

import com.google.gson.annotations.Expose;

public class Position {

    @Expose
    int row;

    @Expose
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
