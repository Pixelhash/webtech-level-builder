package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.View;
import de.codehat.levelbuilder.model.Level;
import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
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
public final class ExportButtonListener implements ActionListener {

    /**
     * The view instance.
     */
    private View view;

    /**
     * Creates a new {@link ExportButtonListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *
     * @param view the view
     */
    public ExportButtonListener(final View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
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
                .map(Tile::getTileType)
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
        level.setName(view.getLevelName());
        level.setNameClean(view.getLevelNameClean());
        level.setTime(view.getLevelTime());
        level.setPossibleGoals(Math.toIntExact(goals));
        level.setRows(view.getLevelRows());
        level.setCols(view.getLevelCols());
        level.setTiles(tiles);

        // open save file dialog
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File(level.getName() + ".json"));
        jFileChooser.setFileFilter(
                new FileNameExtensionFilter("JSON file", "json"));
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (level.export(file)) {
                JOptionPane.showMessageDialog(view,
                        "Saved file to " + file.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(view,
                        "Unable to save file!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
