package de.codehat.levelbuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.swing.*;

class Tile {

    @Expose
    Position position;

    JButton button;

    @Expose
    @SerializedName("type")
    TileType tileType;

    Tile(int x, int y, JButton button, TileType tileType) {
        this.position = new Position(x, y);
        this.button = button;
        this.tileType = tileType;
    }
}
