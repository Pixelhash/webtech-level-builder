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

        // create all required level tiles as buttons
        for (int x = 0; x < levelRows; x++) {
            for (int y = 0; y < levelCols; y++) {
                JButton button = new JButton("t");
                Tile tile = new Tile(x, y, button, TileType.TERRAIN);
                button.setName("tileButton" + x + y);
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
        if (tfLevelName.getText().isEmpty()) {
            return false;
        } else if (tfLevelNameClean.getText().isEmpty()) {
            return false;
        } else if (spLevelTime.getValue().toString().isEmpty()) {
            return false;
        }
        return true;
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
     * Returns the list of tiles.
     *
     * @return the list of tiles
     */
    List<Tile> getTiles() {
        return tiles;
    }
}
