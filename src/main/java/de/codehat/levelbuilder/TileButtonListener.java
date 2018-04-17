package de.codehat.levelbuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class TileButtonListener implements ActionListener {

    private View view;

    public TileButtonListener(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        Optional<Tile> tileOptional = view.getTiles().stream().filter(t -> t.button == button).findFirst();

        // check if tile is present in tiles list
        if (!tileOptional.isPresent()) {
            JOptionPane.showMessageDialog(view, "Tile information wasn't found!");
            return;
        }

        Tile tile = tileOptional.get();

        JComboBox cbLevelTypes = view.getCbLevelTypes();
        int levelTypeIndex = cbLevelTypes.getSelectedIndex();
        LevelType levelType = LevelType.fromIndex(levelTypeIndex);

        // check if index exists
        if (levelType == null) {
            JOptionPane.showMessageDialog(view, "Selected level type is invalid!");
            return;
        }

        // update tile and button
        tile.levelType = levelType;
        button.setText(levelType.abbreviation);
    }
}
