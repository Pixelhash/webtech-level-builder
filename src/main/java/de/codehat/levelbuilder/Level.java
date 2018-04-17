package de.codehat.levelbuilder;

import com.google.gson.annotations.Expose;

/**
 * Represents a level.
 * Consists of a name, a clean name, a time value, amount of possible goals
 * and an array of {@link Tile}.
 *
 * @author Marc-Niclas H. (codehat)
 */
class Level {

    /**
     * level name (internal name)
     */
    @Expose
    String name;

    /**
     * level name clean (human-readable)
     */
    @Expose
    String nameClean;

    /**
     * max time to complete the level
     */
    @Expose
    int time;

    /**
     * amount of goals in this level
     */
    @Expose
    int possibleGoals;

    /**
     * array of tiles representing the level itself
     */
    @Expose
    Tile[] tiles;
}
