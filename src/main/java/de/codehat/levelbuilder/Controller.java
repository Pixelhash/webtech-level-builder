package de.codehat.levelbuilder;

import de.codehat.levelbuilder.listener.*;

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
    static final String VERSION = "1.2.0";

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
    public static final int DEFAULT_LEVEL_ROWS = 8;

    /**
     * Amount of tile columns.
     */
    public static final int DEFAULT_LEVEL_COLS = 8;

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
     * The model instance.
     */
    private Model model;

    /**
     * Creates the controller of the level builder.
     */
    Controller() {
        view = new View(WIDTH, HEIGHT, DEFAULT_LEVEL_ROWS, DEFAULT_LEVEL_COLS);
        model = new Model(DEFAULT_LEVEL_ROWS, DEFAULT_LEVEL_COLS);

        setTileButtonListener();
        setViewListener();

        view.updateTileButtons(model);
        view.updateTitle();
        view.setVisible(true);
    }

    /**
     * Sets the action listener of the buttons from the view.
     */
    private void setViewListener() {
        view.setFileMenuNewListener(new NewLevelButtonListener(view, model, this));
        view.setFileMenuLoadListener(new LoadLevelButtonListener(view, model, this));
        view.setFileMenuSaveListener(new ExportLevelButtonListener(view, model));
        view.setSettingsButtonListener(new SettingsListener(view, model, this));

        // File Menu
        view.setFileMenuExitListener((a) -> System.exit(0));
    }

    public void setTileButtonListener() {
        // Attach a listener to each tile button with its position
        for (int row = 0; row < view.getTileButtons().length; row++) {
            for (int col = 0; col < view.getTileButtons()[row].length; col++) {
                view.getTileButtons()[row][col].addActionListener(new TileButtonListener(view, model, row, col));
            }
        }
    }

    /**
     * Returns the view instance.
     *
     * @return the view instance
     */
    View getView() {
        return view;
    }

    /**
     * Returns the model instance.
     *
     * @return the model instance
     */
    Model getModel() {
        return model;
    }
}
