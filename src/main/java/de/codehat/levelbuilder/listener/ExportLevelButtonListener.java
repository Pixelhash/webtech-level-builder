package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.Model;
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
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Executes if the "Save" button is pressed.
 * Allows you to export the level as JSON document.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class ExportLevelButtonListener implements ActionListener {

    /**
     * The view instance.
     */
    private View view;

    /**
     * The model instance.
     */
    private Model model;

    /**
     * Creates a new {@link ExportLevelButtonListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *
     * @param view the view instance
     * @param model the model instance
     */
    public ExportLevelButtonListener(final View view, Model model) {
        this.view = view;
        this.model = model;
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

        long goals = Arrays.stream(model.getTiles())
                .flatMap(Arrays::stream)
                .map(Tile::getType)
                .filter(tileType -> tileType == TileType.GOAL)
                .count();

        // Check if exactly one goal is selected
        if (goals != 1) {
            JOptionPane.showMessageDialog(view,
                    "Exactly one goal is required!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        long rabbits = Arrays.stream(model.getTiles())
                .flatMap(Arrays::stream)
                .map(Tile::getType)
                .filter(tileType -> tileType == TileType.RABBIT)
                .count();

        // Check if exactly one rabbit is selected
        if (rabbits != 1) {
            JOptionPane.showMessageDialog(view,
                    "Exactly one rabbit is required!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // convert 2D array to 1D array
        Tile[] tiles = Stream.of(model.getTiles()).flatMap(Stream::of).toArray(Tile[]::new);

        // set level
        level.setName(view.getLevelName());
        level.setDescription(view.getLevelDescription());
        level.setTime(view.getLevelTime());
        level.setRows(view.getLevelRows());
        level.setCols(view.getLevelCols());
        level.setTiles(tiles);

        // open save file dialog
        JFileChooser jFileChooser = new JFileChooser();

        // If already saved a level, while open, use the same path again.
        if (model.lastSavePath != null) {
            jFileChooser.setCurrentDirectory(model.lastSavePath);
        }

        jFileChooser.setSelectedFile(new File(level.getName().toLowerCase().replaceAll(" +", "_") + ".json"));
        jFileChooser.setFileFilter(
                new FileNameExtensionFilter("JSON level file", "json"));
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (level.export(file)) {

                model.lastSavePath = file;
                model.lastLoadPath = file;
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
