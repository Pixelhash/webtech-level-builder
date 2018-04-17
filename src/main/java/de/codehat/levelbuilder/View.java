package de.codehat.levelbuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {

    private int levelRows;
    private int levelCols;

    private JTextField tfLevelName;
    private JTextField tfLevelNameClean;
    private JSpinner spLevelTime;
    private JComboBox<String> cbLevelTypes;
    private JButton bExport;

    private JPanel pFields;
    private JPanel pTiles;

    private List<Tile> tiles = new ArrayList<>();

    public View(int width, int height, int levelRows, int levelCols) {
        super();

        this.levelRows = levelRows;
        this.levelCols = levelCols;

        initComponents();

        setSize(width, height);
        setLocationRelativeTo(null); // center frame on start
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // fields panel
        pFields = new JPanel();
        pFields.setLayout(new GridLayout(2, 3));
        add(pFields, BorderLayout.PAGE_START);

        // tile buttons panel
        pTiles = new JPanel();
        pTiles.setLayout(new GridLayout(levelRows, levelCols));
        add(pTiles, BorderLayout.CENTER);

        // level name
        tfLevelName = new JTextField("Level Name");
        pFields.add(tfLevelName);

        // level name clean
        tfLevelNameClean = new JTextField("Level Name clean");
        pFields.add(tfLevelNameClean);

        // level time
        spLevelTime = new JSpinner();
        spLevelTime.setValue(15);
        pFields.add(spLevelTime);

        // level types
        cbLevelTypes = new JComboBox<>(LevelType.TYPES);
        cbLevelTypes.setSelectedIndex(0);
        pFields.add(cbLevelTypes);

        // export level button
        bExport = new JButton("Export");
        pFields.add(bExport);

        // create all required level tiles as buttons
        for (int x = 0; x < levelRows; x++) {
            for (int y = 0; y < levelCols; y++) {
                JButton button = new JButton("t");
                Tile tile = new Tile(x, y, button, LevelType.TERRAIN);
                tiles.add(tile);
                pTiles.add(button);
            }
        }
    }

    public JTextField getTfLevelName() {
        return tfLevelName;
    }

    public JTextField getTfLevelNameClean() {
        return tfLevelNameClean;
    }

    public JSpinner getSpLevelTime() {
        return spLevelTime;
    }

    public JComboBox getCbLevelTypes() {
        return cbLevelTypes;
    }

    public JButton getbExport() {
        return bExport;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
