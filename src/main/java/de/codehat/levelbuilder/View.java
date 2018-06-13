package de.codehat.levelbuilder;

import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Represents the view of the level builder.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class View extends JFrame {

    /**
     * Amount of rows for {@link #pFields} panel.
     */
    private static final int FIELDS_PANEL_ROWS = 5;

    /**
     * Amount of columns for {@link #pFields} panel.
     */
    private static final int FIELDS_PANEL_COLS = 2;

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
     * The top menu bar.
     */
    private JMenuBar menuBar;

    /**
     * The menu for file interaction.
     */
    private JMenu fileMenu;

    /**
     * fileMenu: File -> New
     */
    private JMenuItem newFileItem;

    /**
     * fileMenu: File -> Load
     */
    private JMenuItem loadFileItem;

    /**
     * fileMenu: File -> Save
     * Button to save the level as JSON file.
     */
    private JMenuItem saveFileItem;

    /**
     * fileMenu: File -> Exit
     */
    private JMenuItem exitProgramItem;

    /**
     * Textfield for the level name.
     */
    private JTextField tfLevelName;

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
    private JComboBox<String> cbTileTypes;

    /**
     * Button to adjust settings.
     */
    private JButton bSettings;

    /**
     * The array holding the tile buttons.
     */
    private JButton[][] bTileButtons;

    /**
     * Panel holding the input fields.
     */
    private JPanel pFields;

    /**
     * Panel holding the tile buttons.
     */
    private JPanel pTiles;

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
        this.bTileButtons = new JButton[this.levelRows][this.levelCols];

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
        pFields.setLayout(pFieldsGrid);
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

        // label for level name field
        JLabel lLevelName = new JLabel("Level Name:");
        lLevelName.setName("lLevelName");
        pFields.add(lLevelName);

        // level name
        tfLevelName = new JTextField("Level X");
        tfLevelName.setName("levelName");
        pFields.add(tfLevelName);

        // label for level description field
        JLabel lLevelDescription = new JLabel("Level Description:");
        lLevelDescription.setName("lLevelDescription");
        pFields.add(lLevelDescription);

        // level description
        tfLevelDescription = new JTextField("This is an awesome level.");
        tfLevelDescription.setName("levelDescription");
        pFields.add(tfLevelDescription);

        // label for level time field
        JLabel lLevelTime = new JLabel("Level Time (in seconds):");
        lLevelTime.setName("lLevelTime");
        pFields.add(lLevelTime);

        // level time
        spLevelTime = new JSpinner(new SpinnerNumberModel(15, 1, 120, 1));
        spLevelTime.setName("levelTime");
        pFields.add(spLevelTime);

        // label for tile types field
        JLabel lTileTypes = new JLabel("Selected tile type:");
        lTileTypes.setName("lLevelTypes");
        pFields.add(lTileTypes);

        // tile types
        cbTileTypes = new JComboBox<>(TileType.TYPES);
        cbTileTypes.setName("tileTypes");
        cbTileTypes.setSelectedIndex(TileType.HEDGE_INDEX);
        pFields.add(cbTileTypes);

        pFields.add(new JPanel()); // Add a blank panel to move the button to the right site

        // settingsButton
        bSettings = new JButton("Change Level Size");
        bSettings.setName("settingsButton");
        pFields.add(bSettings);

        // menuBar
        menuBar = new JMenuBar();

        // fileMenu
        fileMenu = new JMenu("File");
        fileMenu.setName("fileMenu");
        menuBar.add(fileMenu);

        // newFileItem
        newFileItem = new JMenuItem("New");
        newFileItem.setName("newFileItem");
        fileMenu.add(newFileItem);

        // loadFileItem
        loadFileItem = new JMenuItem("Load");
        loadFileItem.setName("loadFileItem");
        fileMenu.add(loadFileItem);

        // saveFileItem
        saveFileItem = new JMenuItem("Save");
        saveFileItem.setName("saveFileItem");
        fileMenu.add(saveFileItem);

        JSeparator exitSeparator = new JSeparator(JSeparator.HORIZONTAL);
        fileMenu.add(exitSeparator);

        // exitProgramItem
        exitProgramItem = new JMenuItem("Exit");
        exitProgramItem.setName("exitItem");
        fileMenu.add(exitProgramItem);

        // add menuBar
        this.setJMenuBar(menuBar);

        generateTileButtons(false);
    }

    public void updateTileButtons(Model model) {
        forEachTileButton((row, col) -> {
            JButton button = bTileButtons[row][col];
            TileType tileType = model.getTiles()[row][col].getType();

            button.setText(tileType.getAbbreviation());
            button.setForeground(tileType.getForegroundColor());
            button.setBackground(tileType.getBackgroundColor());
        });
    }

    /**
     * Create all required level tiles as buttons.
     *
     * @param shouldRegenerate deletes old buttons if true, otherwise not.
     */
    public void generateTileButtons(final boolean shouldRegenerate) {
        if (shouldRegenerate) {
            pTiles.removeAll();
            bTileButtons = new JButton[levelRows][levelCols];

            pTiles.setLayout(new GridLayout(levelRows, levelCols));
        }

        for (int row = 0; row < levelRows; row++) {
            for (int col = 0; col < levelCols; col++) {
                JButton button = new JButton("t");
                bTileButtons[row][col] = button;
                button.setName("tileButton" + row + col);
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
                && !tfLevelDescription.getText().isEmpty()
                && !spLevelTime.getValue().toString().isEmpty();
    }

    /**
     * Asks the user to set the amount of tile rows and tile columns.
     */
    public boolean askForRowsAndCols() {
        JSpinner sRows = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));
        JSpinner sCols = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));

        sRows.setName("rowsSpinner");
        sCols.setName("colsSpinner");

        Object[] message = {
                "This action will RESET all tiles!",
                "Tile Rows (6-10):", sRows,
                "Tile Cols (6-10):", sCols
        };

        int option = JOptionPane.showConfirmDialog(this,
                message, "Level Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            levelRows = Integer.valueOf(sRows.getValue().toString());
            levelCols = Integer.valueOf(sCols.getValue().toString());
            return true;
        }
        return false;
    }

    /**
     * Accepts a function, which is run against all buttons.
     * It passes the row and the col through.
     *
     * @param func the function, which is run for each tile button.
     */
    public void forEachTileButton(BiConsumer<Integer, Integer> func) {
        for (int row = 0; row < levelRows; row++) {
            for (int col = 0; col < levelCols; col++) {
                func.accept(row, col);
            }
        }
    }

    /**
     * Resets the input of all fields to their default values.
     */
    public void resetAllFieldsText() {
        tfLevelName.setText("Level X");
        tfLevelDescription.setText("This is an awesome level.");
        spLevelTime.setValue(15);
        cbTileTypes.setSelectedIndex(TileType.HEDGE_INDEX);
    }

    /**
     * Updates the title according to amount of tile rows/cols.
     */
    public void updateTitle() {
        setTitle(String.format("%s v%s - Size: %d x %d",
                Controller.NAME, Controller.VERSION, levelRows, levelCols));
    }

    /**
     * Returns the tile buttons array.
     *
     * @return the tile buttons array.
     */
    public JButton[][] getTileButtons() {
        return bTileButtons;
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
     * Sets the level name.
     *
     * @param levelName the new level name
     */
    public void setLevelName(String levelName) {
        tfLevelName.setText(levelName);
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
     * Sets the level description.
     *
     * @param levelDescription the new level description
     */
    public void setLevelDescription(String levelDescription) {
        tfLevelDescription.setText(levelDescription);
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
     * Sets the max-level-completion time.
     *
     * @param levelTime the new max-level-completion time
     */
    public void setLevelTime(int levelTime) {
        spLevelTime.setValue(levelTime);
    }

    /**
     * Returns the index of the currently selected item.
     *
     * @return index of the currently selected item
     */
    public int getSelectedLevelTypeIndex() {
        return cbTileTypes.getSelectedIndex();
    }

    /**
     * Sets the index of the currently selected item.
     *
     * @param index the new index of the currently selected item
     */
    public void setSelectedLevelTypeIndex(int index) {
        cbTileTypes.setSelectedIndex(index);
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
     * Sets the action listener of the File -> New menu item.
     *
     * @param listener the action listener
     */
    void setFileMenuNewListener(final ActionListener listener) {
        newFileItem.addActionListener(listener);
    }

    /**
     * Sets the action listener of the File -> Load menu item.
     *
     * @param listener the action listener
     */
    void setFileMenuLoadListener(final ActionListener listener) {
        loadFileItem.addActionListener(listener);
    }

    /**
     * Sets the action listener of the File -> Save menu item.
     *
     * @param listener the action listener
     */
    void setFileMenuSaveListener(final ActionListener listener) {
        saveFileItem.addActionListener(listener);
    }

    /**
     * Sets the action listener of the File -> Exit menu item.
     *
     * @param listener the action listener
     */
    void setFileMenuExitListener(final ActionListener listener) {
        exitProgramItem.addActionListener(listener);
    }

    /**
     * Returns the amount of tile rows.
     *
     * @return the amount of tile rows
     */
    public int getLevelRows() {
        return levelRows;
    }

    /**
     * Sets the amount of tile rows.
     *
     * @param levelRows the amount of tile rows
     */
    public void setLevelRows(int levelRows) {
        this.levelRows = levelRows;
    }

    /**
     * Returns the amount of tile columns.
     *
     * @return the amount of tile columns
     */
    public int getLevelCols() {
        return levelCols;
    }

    /**
     * Sets the amount of tile columns.
     *
     * @param levelCols the amount of tile columns
     */
    public void setLevelCols(int levelCols) {
        this.levelCols = levelCols;
    }
}
