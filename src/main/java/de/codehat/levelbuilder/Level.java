package de.codehat.levelbuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javax.swing.*;
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

    /**
     *  Exports the current level as JSON document and saves it.
     *
     * @param file the file location for the JSON document
     * @return true if exported, false on error (e.g IO error)
     */
    boolean export(File file) {
        // build JSON string
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        final String levelJson = gson.toJson(this);

        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(levelJson);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println("Couldn't save file to " + file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
