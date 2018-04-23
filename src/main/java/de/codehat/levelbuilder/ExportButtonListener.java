package de.codehat.levelbuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Executes if the "Export" button is pressed.
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
        // check if all input fields are filled
        if (!view.areFieldsNotEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Make sure you have filled all input fields!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Level level = new Level();

        long goals = view.getTiles().stream()
                .map(tile -> tile.tileType)
                .filter(tileType -> tileType == TileType.GOAL)
                .count();

        if (goals == 0) {
            JOptionPane.showMessageDialog(view,
                    "At least one goal is required!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // convert list to array
        Tile[] tiles = new Tile[view.getTiles().size()];
        tiles = view.getTiles().toArray(tiles);

        // set level
        level.name = view.getLevelName();
        level.nameClean = view.getLevelNameClean();
        level.time = view.getLevelTime();
        level.possibleGoals = Math.toIntExact(goals);
        level.rows = view.getLevelRows();
        level.cols = view.getLevelCols();
        level.tiles = tiles;

        // open save file dialog
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File(level.name + ".json"));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON file", "json"));
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (level.export(file)) {
                JOptionPane.showMessageDialog(view, "Saved file to " + file.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(view,
                        "Unable to save file!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
