package de.codehat.levelbuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Executes of the "Export" button is pressed.
 * Allows you to export the level as JSON document.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class ExportButtonListener implements ActionListener {

    /**
     * the view instance
     */
    private View view;

    /**
     * Creates a new {@link ExportButtonListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *
     * @param view the view
     */
    ExportButtonListener(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Level level = new Level();

        long goals = view.getTiles().stream()
                .map(tile -> tile.tileType)
                .filter(levelType -> levelType == TileType.GOAL)
                .count();

        if (goals == 0) {
            JOptionPane.showMessageDialog(view, "At least one goal is required!");
            return;
        }

        // convert list to array
        Tile[] tiles = new Tile[view.getTiles().size()];
        tiles = view.getTiles().toArray(tiles);

        level.possibleGoals = Math.toIntExact(goals);
        level.name = view.getLevelName();
        level.nameClean = view.getLevelNameClean();
        level.time = view.getLevelTime();
        level.tiles = tiles;

        // build JSON string
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        final String levelJson = gson.toJson(level);

        // open save file dialog
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            FileWriter fw;
            try {
                fw = new FileWriter(file);
                fw.write(levelJson);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.err.println("Couldn't save file to " + file.getAbsolutePath());
                JOptionPane.showMessageDialog(view, "Unable to save file!");
                e.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(view, "Saved file to " + file.getAbsolutePath());
        }
    }
}
