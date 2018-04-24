package de.codehat.levelbuilder.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a level.
 * Consists of a name, a clean name, a time value, amount of possible goals
 * and an array of {@link Tile}.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class Level {

    /**
     * Level name (internal name).
     */
    @Expose
    private String name;

    /**
     * Level name clean (human-readable).
     */
    @Expose
    private String nameClean;

    /**
     * Max time to complete the level.
     */
    @Expose
    private int time;

    /**
     * Amount of goals in this level.
     */
    @Expose
    private int possibleGoals;

    /**
     * Amount of tile rows.
     */
    @Expose
    private int rows;

    /**
     * Amount of tile columns.
     */
    @Expose
    private int cols;

    /**
     * Array of tiles representing the level itself.
     */
    @Expose
    private Tile[] tiles;

    /**
     * Exports the current level as JSON document and saves it.
     *
     * @param file the file location for the JSON document
     * @return true if exported, false on error (e.g IO error)
     */
    public boolean export(final File file) {
        // build JSON string
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        final String levelJson = gson.toJson(this);

        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(levelJson);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println("Couldn't save file to "
                    + file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Returns the level name.
     *
     * @return the level name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the level name.
     *
     * @param name the level name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the human-readable level name.
     *
     * @return the human-readable level name
     */
    public String getNameClean() {
        return nameClean;
    }

    /**
     * Sets the human-readable level name.
     *
     * @param nameClean the human-readable level name to set
     */
    public void setNameClean(final String nameClean) {
        this.nameClean = nameClean;
    }

    /**
     * Returns the maximum level-completion time.
     *
     * @return the maximum level-completion time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the maximum level-completion time.
     *
     * @param time the maximum level-completion time to set
     */
    public void setTime(final int time) {
        this.time = time;
    }

    /**
     * Returns the amount of possible goals in this level.
     *
     * @return the amount of possible goals
     */
    public int getPossibleGoals() {
        return possibleGoals;
    }

    /**
     * Sets the amount of possible goals in this level.
     *
     * @param possibleGoals the amount of possible goals to set
     */
    public void setPossibleGoals(final int possibleGoals) {
        this.possibleGoals = possibleGoals;
    }

    /**
     * Returns the amount of tile rows.
     *
     * @return the amount of tile rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the amount of tile rows.
     *
     * @param rows the amount of tile rows to set.
     */
    public void setRows(final int rows) {
        this.rows = rows;
    }

    /**
     * Returns the amount of tile columns.
     *
     * @return the amount of tile columns.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Sets the amount of tile columns.
     *
     * @param cols the amount of tile columns to set
     */
    public void setCols(final int cols) {
        this.cols = cols;
    }

    /**
     * Returns an array of tiles this level consists of.
     *
     * @return an array of tiles this level consists of
     */
    public Tile[] getTiles() {
        return tiles;
    }

    /**
     * Sets the array of tiles this level consists of.
     *
     * @param tiles an array of tiles to set
     */
    public void setTiles(final Tile[] tiles) {
        this.tiles = tiles;
    }
}
