package de.codehat.levelbuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the view of the level builder.
 *
 * @author Marc-Niclas H. (codehat)
 *
 */
class View extends JFrame {

    /**
     * amount of rows
     */
    private int levelRows;

    /**
     * amount of columns
     */
    private int levelCols;

    /**
     * textfield for the level name
     */
    private JTextField tfLevelName;

    /**
     * textfield for the clean level name
     */
    private JTextField tfLevelNameClean;

    /**
     * field for the max-level-completion time
     */
    private JSpinner spLevelTime;

    /**
     * selection of all possible tile types
     */
    private JComboBox<String> cbLevelTypes;

    /**
     * button to export the level as JSON file
     */
    private JButton bExport;

    /**
     * button to adjust settings
     */
    private JButton bSettings;

    /**
     * panel holding the input fields
     */
    private JPanel pFields;

    /**
     * panel holding the tiles (represented as buttons)
     */
    private JPanel pTiles;

    /**
     * list holding all tiles with their information
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * Creates a new view object with the given window information.
     *
     * @param width window width
     * @param height windows height
     * @param levelRows amount of rows
     * @param levelCols amount of columns
     */
    View(int width, int height, int levelRows, int levelCols) {
        super();

        this.levelRows = levelRows;
        this.levelCols = levelCols;

        setSize(width, height);
        setLocationRelativeTo(null); // center frame on start
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setName("mainFrame");

        initComponents();
    }

    /**
     * Initialises all components of the window.
     */
    private void initComponents() {
        // fields grid layout
        GridLayout pFieldsGrid = new GridLayout(2, 3);
        pFieldsGrid.setHgap(5);
        pFieldsGrid.setVgap(5);

        // fields panel
        pFields = new JPanel();
        pFields.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pFields.setLayout(pFieldsGrid);
        add(pFields, BorderLayout.PAGE_START);

        // tile buttons panel
        pTiles = new JPanel();
        pTiles.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pTiles.setLayout(new GridLayout(levelRows, levelCols));
        add(pTiles, BorderLayout.CENTER);

        // level name
        tfLevelName = new JTextField("level_name");
        tfLevelName.setName("levelName");
        pFields.add(tfLevelName);

        // level name clean
        tfLevelNameClean = new JTextField("Level Name Clean");
        tfLevelNameClean.setName("levelNameClean");
        pFields.add(tfLevelNameClean);

        // level time
        spLevelTime = new JSpinner(new SpinnerNumberModel(15, 1, 120, 1));
        spLevelTime.setName("levelTime");
        pFields.add(spLevelTime);

        // level types
        cbLevelTypes = new JComboBox<>(TileType.TYPES);
        cbLevelTypes.setName("levelTypes");
        cbLevelTypes.setSelectedIndex(0);
        pFields.add(cbLevelTypes);

        // export level button
        bExport = new JButton("Export");
        bExport.setName("exportButton");
        pFields.add(bExport);

        // settingsButton
        bSettings = new JButton("Settings");
        bSettings.setName("settingsButton");
        pFields.add(bSettings);

        generateTileButtons(false);
    }

    /**
     * Create all required level tiles as buttons
     *
     * @param shouldRegenerate deletes old buttons if true, otherwise not.
     */
    void generateTileButtons(boolean shouldRegenerate) {
        ActionListener listener = null;
        if (shouldRegenerate) {
            listener = tiles.get(0).button.getActionListeners()[0];
            tiles.stream().map(t -> t.button).forEach(b -> pTiles.remove(b));
            tiles.clear();
            pTiles.setLayout(new GridLayout(levelRows, levelCols));
        }

        for (int x = 0; x < levelRows; x++) {
            for (int y = 0; y < levelCols; y++) {
                JButton button = new JButton("t");
                Tile tile = new Tile(x, y, button, TileType.TERRAIN);
                button.setName("tileButton" + x + y);
                if (shouldRegenerate) button.addActionListener(listener);
                //button.setOpaque(true);
                button.setForeground(tile.tileType.foregroundColor);
                button.setBackground(tile.tileType.backgroundColor);
                tiles.add(tile);
                pTiles.add(button);
            }
        }
    }

    /**
     * Checks if all necessary input fields aren't empty.
     *
     * @return true if no field is empty, false if at least one field is empty
     */
    boolean areFieldsNotEmpty() {
        return !tfLevelName.getText().isEmpty() && !tfLevelNameClean.getText().isEmpty()
                && !spLevelTime.getValue().toString().isEmpty();
    }

    /**
     * Asks the user to set the amount of tile rows and tile columns.
     */
    void askForRowsAndCols() {
        JSpinner sRows = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));
        JSpinner sCols = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));
        Object[] message = {
                "Tile Rows (6-12):", sRows,
                "Tile Cols (6-12):", sCols
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Level Size", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            levelRows = Integer.valueOf(sRows.getValue().toString());
            levelCols = Integer.valueOf(sCols.getValue().toString());
        }
    }

    /**
     * Updates the title according to amount of tile rows/cols.
     */
    void updateTitle() {
        setTitle(String.format("%s v%s - Size: %d x %d", Controller.NAME, Controller.VERSION, levelRows, levelCols));
    }

    /**
     * Returns the level name.
     *
     * @return the level name
     */
    String getLevelName() {
        return tfLevelName.getText();
    }

    /**
     * Returns the clean level name.
     *
     * @return the clean level name
     */
    String getLevelNameClean() {
        return tfLevelNameClean.getText();
    }

    /**
     * Returns the max-level-completion time.
     *
     * @return the max-level-completion time
     */
    int getLevelTime() {
        return Integer.parseInt(spLevelTime.getValue().toString());
    }

    /**
     * Returns the index of the currently selected item.
     *
     * @return index of the currently selected item
     */
    int getSelectecLevelTypeIndex() {
        return cbLevelTypes.getSelectedIndex();
    }

    /**
     * Sets the action listener of the "Export" button.
     *
     * @param listener the action listener
     */
    void setExportButtonListener(ActionListener listener) {
        bExport.addActionListener(listener);
    }

    /**
     * Sets the action listener of the "Settings" button.
     *
     * @param listener the action listener
     */
    void setSettingsButtonListener(ActionListener listener) {
        bSettings.addActionListener(listener);
    }

    /**
     * Returns the list of tiles.
     *
     * @return the list of tiles
     */
    List<Tile> getTiles() {
        return tiles;
    }

    /**
     * Returns the amount of tile rows.
     *
     * @return the amount of tile rows.
     */
    public int getLevelRows() {
        return levelRows;
    }

    /**
     * Returns the amount of tile columns.
     *
     * @return the amount of tile columns.
     */
    public int getLevelCols() {
        return levelCols;
    }
}
