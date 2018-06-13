package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.Controller;
import de.codehat.levelbuilder.Model;
import de.codehat.levelbuilder.View;
import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Executes if the "Settings" button is pressed.
 * Allows you to adjust some settings.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class SettingsListener implements ActionListener {

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
     * Creates a new {@link SettingsListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *  @param view the view instance
     * @param model the model instance
     * @param controller the controller instance
     */
    public SettingsListener(final View view, Model model, Controller controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        // Check, if the user pressed "OK"
        if (view.askForRowsAndCols()) {
            model.setTiles(new Tile[view.getLevelRows()][view.getLevelCols()]);
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
