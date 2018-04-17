package de.codehat.levelbuilder;

import javax.swing.*;

/**
 * Represents the controller of the level builder.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class Controller {

    /**
     * program name
     */
    private static final String NAME = "LevelBuilder";

    /**
     * program version
     */
    private static final String VERSION = "v1.0.1";

    /**
     * window width
     */
    private static final int WIDTH = 400;

    /**
     * window height
     */
    private static final int HEIGHT = 600;

    /**
     * amount of tile rows
     */
    private static final int LEVEL_ROWS = 8;

    /**
     * amount of tile columns
     */
    private static final int LEVEL_COLS = 8;

    /**
     * Initiates the level builder.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }

    /**
     * The view instance
     */
    private View view;

    /**
     * Creates the controller of the level builder.
     */
    private Controller() {
        view = new View(WIDTH, HEIGHT, LEVEL_ROWS, LEVEL_COLS);

        setViewListener();

        view.setTitle(NAME + " " + VERSION);
        view.setVisible(true);
    }

    /**
     * Sets the action listener of the buttons from the view.
     */
    private void setViewListener() {
        TileButtonListener tileButtonListener = new TileButtonListener(view);
        ExportButtonListener exportButtonListener = new ExportButtonListener(view);

        view.getTiles().stream()
                .map(tile -> tile.button)
                .forEach(button -> button.addActionListener(tileButtonListener));

        view.setExportButtonListener(exportButtonListener);
    }

}
