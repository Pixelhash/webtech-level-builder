package de.codehat.levelbuilder;

import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import javax.swing.SpinnerNumberModel;
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
public class View extends JFrame {

    /**
     * Amount of rows for {@link #pFields} panel.
     */
    private static final int FIELDS_PANEL_ROWS = 3;

    /**
     * Amount of columns for {@link #pFields} panel.
     */
    private static final int FIELDS_PANEL_COLS = 3;

    /**
     * Border size for all panels.
     */
    private static final int PANEL_BORDER_SIZE = 5;

    /**
     * Horizontal and vertical gap for panels.
     */
    private static final int PANEL_GAP = 5;

    /**
     * Amount of rows.
     */
    private int levelRows;

    /**
     * Amount of columns.
     */
    private int levelCols;

    /**
     * Textfield for the level name.
     */
    private JTextField tfLevelName;

    /**
     * Textfield for the clean level name.
     */
    private JTextField tfLevelNameClean;

    /**
     * Textfield for the level description.
     */
    private JTextField tfLevelDescription;

    /**
     * Field for the max-level-completion time.
     */
    private JSpinner spLevelTime;

    /**
     * Selection of all possible tile types.
     */
    private JComboBox<String> cbLevelTypes;

    /**
     * Button to export the level as JSON file.
     */
    private JButton bExport;

    /**
     * Button to adjust settings.
     */
    private JButton bSettings;

    /**
     * Panel holding the input fields.
     */
    private JPanel pFields;

    /**
     * Panel holding the tiles (represented as buttons).
     */
    private JPanel pTiles;

    /**
     * List holding all tiles with their information.
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
    View(final int width, final int height,
         final int levelRows, final int levelCols) {
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
        GridLayout pFieldsGrid = new GridLayout(FIELDS_PANEL_ROWS,
                FIELDS_PANEL_COLS);
        pFieldsGrid.setHgap(PANEL_GAP);
        pFieldsGrid.setVgap(PANEL_GAP);

        // fields panel
        pFields = new JPanel();
        pFields.setBorder(BorderFactory.createEmptyBorder(
                PANEL_BORDER_SIZE,
                PANEL_BORDER_SIZE,
                PANEL_BORDER_SIZE,
                PANEL_BORDER_SIZE
        ));
        pFields.setLayout(new GridBagLayout()); //(pFieldsGrid);
        add(pFields, BorderLayout.PAGE_START);

        // tile buttons panel
        pTiles = new JPanel();
        pTiles.setBorder(BorderFactory.createEmptyBorder(
                PANEL_BORDER_SIZE,
                PANEL_BORDER_SIZE,
                PANEL_BORDER_SIZE,
                PANEL_BORDER_SIZE
        ));
        pTiles.setLayout(new GridLayout(levelRows, levelCols));
        add(pTiles, BorderLayout.CENTER);

        // level name
        GridBagConstraints tfLevelNameConst = new GridBagConstraints();
        tfLevelNameConst.fill = GridBagConstraints.NONE;
        tfLevelNameConst.insets = new Insets(3, 3, 3, 3);
        tfLevelNameConst.anchor = GridBagConstraints.FIRST_LINE_START;
        tfLevelNameConst.gridx  = 0;
        tfLevelNameConst.gridy = 0;
        tfLevelName = new JTextField("level_name");
        tfLevelName.setName("levelName");
        pFields.add(tfLevelName, tfLevelNameConst);

        // level name clean
        GridBagConstraints tfLevelNameCleanConst = new GridBagConstraints();
        tfLevelNameCleanConst.fill = GridBagConstraints.NONE;
        tfLevelNameCleanConst.insets = new Insets(3, 3, 3, 3);
        tfLevelNameCleanConst.anchor = GridBagConstraints.CENTER;
        tfLevelNameCleanConst.gridx  = 1;
        tfLevelNameCleanConst.gridy = 0;
        tfLevelNameClean = new JTextField("Level Name Clean");
        tfLevelNameClean.setName("levelNameClean");
        pFields.add(tfLevelNameClean, tfLevelNameCleanConst);

        // level description
        GridBagConstraints tfLevelDescriptionConst = new GridBagConstraints();
        tfLevelDescriptionConst.fill = GridBagConstraints.NONE;
        tfLevelDescriptionConst.insets = new Insets(3, 3, 3, 3);
        tfLevelDescriptionConst.anchor = GridBagConstraints.FIRST_LINE_END;
        tfLevelDescriptionConst.gridx  = 2;
        tfLevelDescriptionConst.gridy = 0;
        tfLevelDescription = new JTextField("Level Description");
        tfLevelDescription.setName("levelDescription");
        pFields.add(tfLevelDescription, tfLevelDescriptionConst);

        // level time
        GridBagConstraints spLevelTimeConst = new GridBagConstraints();
        spLevelTimeConst.fill = GridBagConstraints.NONE;
        spLevelTimeConst.insets = new Insets(3, 3, 3, 3);
        spLevelTimeConst.anchor = GridBagConstraints.FIRST_LINE_START;
        spLevelTimeConst.gridx  = 0;
        spLevelTimeConst.gridy = 1;
        spLevelTime = new JSpinner(new SpinnerNumberModel(15, 1, 120, 1));
        spLevelTime.setName("levelTime");
        pFields.add(spLevelTime, spLevelTimeConst);

        // level types
        GridBagConstraints cbLevelTypesConst = new GridBagConstraints();
        cbLevelTypesConst.fill = GridBagConstraints.NONE;
        cbLevelTypesConst.insets = new Insets(3, 3, 3, 3);
        cbLevelTypesConst.anchor = GridBagConstraints.CENTER;
        cbLevelTypesConst.gridx  = 1;
        cbLevelTypesConst.gridy = 1;
        cbLevelTypes = new JComboBox<>(TileType.TYPES);
        cbLevelTypes.setName("levelTypes");
        cbLevelTypes.setSelectedIndex(0);
        pFields.add(cbLevelTypes, cbLevelTypesConst);

        // export level button
        GridBagConstraints bExportConst = new GridBagConstraints();
        bExportConst.fill = GridBagConstraints.NONE;
        bExportConst.insets = new Insets(3, 3, 3, 3);
        bExportConst.anchor = GridBagConstraints.FIRST_LINE_START;
        bExportConst.gridx  = 0;
        bExportConst.gridy = 2;
        bExport = new JButton("Export");
        bExport.setName("exportButton");
        pFields.add(bExport, bExportConst);

        // settingsButton
        GridBagConstraints bSettingsConst = new GridBagConstraints();
        bSettingsConst.fill = GridBagConstraints.NONE;
        bSettingsConst.insets = new Insets(3, 3, 3, 3);
        bSettingsConst.anchor = GridBagConstraints.CENTER;
        bSettingsConst.gridx  = 1;
        bSettingsConst.gridy = 2;
        bSettings = new JButton("Settings");
        bSettings.setName("settingsButton");
        pFields.add(bSettings, bSettingsConst);

        generateTileButtons(false);
    }

    /**
     * Create all required level tiles as buttons.
     *
     * @param shouldRegenerate deletes old buttons if true, otherwise not.
     */
    public void generateTileButtons(final boolean shouldRegenerate) {
        ActionListener listener = null;
        if (shouldRegenerate) {
            listener = tiles.get(0).getButton().getActionListeners()[0];
            tiles.stream()
                    .map(Tile::getButton)
                    .forEach(b -> pTiles.remove(b));
            tiles.clear();
            pTiles.setLayout(new GridLayout(levelRows, levelCols));
        }

        for (int row = 0; row < levelRows; row++) {
            for (int col = 0; col < levelCols; col++) {
                JButton button = new JButton("t");
                Tile tile = new Tile(row, col, button, TileType.TERRAIN);
                button.setName("tileButton" + row + col);
                if (shouldRegenerate) {
                    button.addActionListener(listener);
                }
                //button.setOpaque(true);
                button.setForeground(tile.getTileType().getForegroundColor());
                button.setBackground(tile.getTileType().getBackgroundColor());
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
    public boolean areFieldsNotEmpty() {
        return !tfLevelName.getText().isEmpty()
                && !tfLevelNameClean.getText().isEmpty()
                && !spLevelTime.getValue().toString().isEmpty();
    }

    /**
     * Asks the user to set the amount of tile rows and tile columns.
     */
    public void askForRowsAndCols() {
        JSpinner sRows = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));
        JSpinner sCols = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));

        sRows.setName("rowsSpinner");
        sCols.setName("colsSpinner");

        Object[] message = {
                "Tile Rows (6-10):", sRows,
                "Tile Cols (6-10):", sCols
        };

        int option = JOptionPane.showConfirmDialog(this,
                message, "Level Size", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            levelRows = Integer.valueOf(sRows.getValue().toString());
            levelCols = Integer.valueOf(sCols.getValue().toString());
        }
    }

    /**
     * Updates the title according to amount of tile rows/cols.
     */
    public void updateTitle() {
        setTitle(String.format("%s v%s - Size: %d x %d",
                Controller.NAME, Controller.VERSION, levelRows, levelCols));
    }

    /**
     * Returns the level name.
     *
     * @return the level name
     */
    public String getLevelName() {
        return tfLevelName.getText();
    }

    /**
     * Returns the clean level name.
     *
     * @return the clean level name
     */
    public String getLevelNameClean() {
        return tfLevelNameClean.getText();
    }

    /**
     * Returns the level description.
     *
     * @return the level description
     */
    public String getLevelDescription() {
        return tfLevelDescription.getText();
    }

    /**
     * Returns the max-level-completion time.
     *
     * @return the max-level-completion time
     */
    public int getLevelTime() {
        return Integer.parseInt(spLevelTime.getValue().toString());
    }

    /**
     * Returns the index of the currently selected item.
     *
     * @return index of the currently selected item
     */
    public int getSelectedLevelTypeIndex() {
        return cbLevelTypes.getSelectedIndex();
    }

    /**
     * Sets the action listener of the "Export" button.
     *
     * @param listener the action listener
     */
    void setExportButtonListener(final ActionListener listener) {
        bExport.addActionListener(listener);
    }

    /**
     * Sets the action listener of the "Settings" button.
     *
     * @param listener the action listener
     */
    void setSettingsButtonListener(final ActionListener listener) {
        bSettings.addActionListener(listener);
    }

    /**
     * Returns the list of tiles.
     *
     * @return the list of tiles
     */
    public List<Tile> getTiles() {
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
