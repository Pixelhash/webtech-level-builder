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
    private static final String VERSION = "1.1.0";

    /**
     * window width
     */
    private static final int WIDTH = 500;

    /**
     * window height
     */
    private static final int HEIGHT = 600;

    /**
     * amount of tile rows
     */
    private int levelRows;

    /**
     * amount of tile columns
     */
    private int levelCols;

    /**
     * Initiates the level builder.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
//            System.err.println("Unable to get and set look and feel of this system!");
//            e.printStackTrace();
//        }

        SwingUtilities.invokeLater(Controller::new);
    }

    /**
     * The view instance
     */
    private View view;

    /**
     * Creates the controller of the level builder.
     */
    Controller() {
        askForRowsAndCols();

        view = new View(WIDTH, HEIGHT, levelRows, levelCols);

        setViewListener();

        view.setTitle(String.format("%s v%s - Size: %d x %d", NAME, VERSION, levelRows, levelCols));
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

    /**
     * Asks the user to set the amount of tile rows and tile columns.
     */
    private void askForRowsAndCols() {
        JSpinner sRows = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));
        JSpinner sCols = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));
        Object[] message = {
                "Tile Rows (6-12):", sRows,
                "Tile Cols (6-12):", sCols
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Level Size", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            levelRows = Integer.valueOf(sRows.getValue().toString());
            levelCols = Integer.valueOf(sCols.getValue().toString());
        } else {
            JOptionPane.showMessageDialog(null, "Using default values (rows: 8, cols: 8).");

            levelRows = 8;
            levelCols = 8;
        }
    }

    /**
     * Returns the view instance
     *
     * @return the view
     */
    View getView() {
        return view;
    }
}
