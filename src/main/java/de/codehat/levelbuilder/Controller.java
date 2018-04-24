package de.codehat.levelbuilder;

import de.codehat.levelbuilder.listener.ExportButtonListener;
import de.codehat.levelbuilder.listener.SettingsListener;
import de.codehat.levelbuilder.listener.TileButtonListener;
import de.codehat.levelbuilder.model.Tile;

import javax.swing.SwingUtilities;

/**
 * Represents the controller of the level builder.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class Controller {

    /**
     * Program name.
     */
    static final String NAME = "LevelBuilder";

    /**
     * Program version.
     */
    static final String VERSION = "1.1.0";

    /**
     * Window width.
     */
    private static final int WIDTH = 500;

    /**
     * Window height.
     */
    private static final int HEIGHT = 600;

    /**
     * Amount of tile rows.
     */
    private static final int DEFAULT_LEVEL_ROWS = 8;

    /**
     * Amount of tile columns.
     */
    private static final int DEFAULT_LEVEL_COLS = 8;

    /**
     * Initiates the level builder.
     *
     * @param args program arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }

    /**
     * The view instance.
     */
    private View view;

    /**
     * Creates the controller of the level builder.
     */
    Controller() {
        view = new View(WIDTH, HEIGHT, DEFAULT_LEVEL_ROWS, DEFAULT_LEVEL_COLS);

        setViewListener();

        view.updateTitle();
        view.setVisible(true);
    }

    /**
     * Sets the action listener of the buttons from the view.
     */
    private void setViewListener() {
        TileButtonListener tileButtonListener = new TileButtonListener(view);

        view.getTiles().stream()
                .map(Tile::getButton)
                .forEach(button -> button.addActionListener(tileButtonListener));

        view.setExportButtonListener(new ExportButtonListener(view));
        view.setSettingsButtonListener(new SettingsListener(view));
    }

    /**
     * Returns the view instance.
     *
     * @return the view
     */
    View getView() {
        return view;
    }
}
