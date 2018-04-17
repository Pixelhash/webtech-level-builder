package de.codehat.levelbuilder;

import com.google.gson.annotations.Expose;

public class Level {

    @Expose
    String name;

    @Expose
    String nameClean;

    @Expose
    int time;

    @Expose
    int possibleGoals;

    @Expose
    Tile[] tiles;
}
