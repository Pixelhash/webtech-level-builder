package de.codehat.levelbuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.swing.*;

public class Tile {

    @Expose
    Position position;

    JButton button;

    @Expose
    @SerializedName("type")
    LevelType levelType;

    public Tile(int x, int y, JButton button, LevelType levelType) {
        this.position = new Position(x, y);
        this.button = button;
        this.levelType = levelType;
    }
}
