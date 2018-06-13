package de.codehat.levelbuilder.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a level.
 * Consists of a name, a clean name, a time value, amount of possible goals
 * and an array of {@link Tile}.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class Level {

    /**
     * Level name.
     */
    private String name;

    /**
     * Level description (should be not more than 20-40 characters).
     */
    private String description;

    /**
     * Max time to complete the level.
     */
    private int time;

    /**
     * Amount of tile rows.
     */
    private int rows;

    /**
     * Amount of tile columns.
     */
    private int cols;

    /**
     * Array of tiles representing the level itself.
     */
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
     * Returns the level description.
     *
     * @return the level description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the level description.
     *
     * @param description the level description to set
     */
    public void setDescription(final String description) {
        this.description = description;
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

    @Override
    public boolean equals(final Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Level l = (Level) o;
        return name.equals(l.getName()) && time == l.getTime()
                && rows == l.getRows() && cols == l.getCols()
                && Arrays.deepEquals(tiles, l.getTiles());
    }
}
