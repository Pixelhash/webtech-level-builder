package de.codehat.levelbuilder.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.codehat.levelbuilder.Controller;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Executes if the "Load" menu button is pressed.
 * Allows you to start from scratch.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class LoadLevelButtonListener implements ActionListener {

    /**
     * The view instance.
     */
    private View view;

    /**
     * The model instance.
     */
    private Model model;

    /**
     * The controller instance.
     */
    private Controller controller;

    /**
     * Creates a new {@link LoadLevelButtonListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *  @param view the view instance
     * @param model the model instance
     * @param controller the controller instance
     */
    public LoadLevelButtonListener(final View view, Model model, Controller controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON level file", "json");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder
                    .setPrettyPrinting()
                    .create();

            try {
                Level level = gson.fromJson(new FileReader(chooser.getSelectedFile()), Level.class);

                view.setLevelRows(level.getRows());
                view.setLevelCols(level.getCols());
                view.setLevelName(level.getName());
                view.setLevelDescription(level.getDescription());
                view.setLevelTime(level.getTime());
                model.setTiles(new Tile[level.getRows()][level.getCols()]);
                Arrays.stream(level.getTiles())
                        .forEach((tile) -> model.getTiles()[tile.getPosition().getRow()][tile.getPosition().getCol()] = tile);

                view.generateTileButtons(true);
                controller.setTileButtonListener();
                view.updateTileButtons(model);
                view.revalidate();
                view.repaint();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(view,
                        "Unable to open file!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
