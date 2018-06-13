package de.codehat.levelbuilder.listener;

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
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Executes if the "New" menu button is pressed.
 * Allows you to start from scratch.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class NewLevelButtonListener implements ActionListener {

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
     * Creates a new {@link NewLevelButtonListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *  @param view the view instance
     * @param model the model instance
     * @param controller the controller instance
     */
    public NewLevelButtonListener(final View view, Model model, Controller controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        // ask the user, if he really wants to do this
        int reply = JOptionPane.showConfirmDialog(view,
                "<html><body>Are you sure?<br>This will DELETE your current progress!</body></html>",
                "New Level",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (reply == JOptionPane.YES_OPTION) {
            view.setLevelRows(Controller.DEFAULT_LEVEL_ROWS);
            view.setLevelCols(Controller.DEFAULT_LEVEL_COLS);

            view.resetAllFieldsText();
            model.setTiles(new Tile[Controller.DEFAULT_LEVEL_ROWS][Controller.DEFAULT_LEVEL_COLS]);
            model.fillAllTilesWith(TileType.TERRAIN);

            view.generateTileButtons(true);
            controller.setTileButtonListener();
            view.updateTileButtons(model);
            view.revalidate();
            view.repaint();
            view.updateTitle();
        }
    }
}
