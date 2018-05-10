package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.View;
import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * The listener for the tile buttons.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class TileButtonListener implements ActionListener {

    /**
     * The view instance.
     */
    private View view;

    /**
     * Creates a new listener for the tile buttons.
     *
     * @param view the view instance
     */
    public TileButtonListener(final View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        JButton button = (JButton) event.getSource();
        Optional<Tile> tileOptional = view.getTiles().stream()
                .filter(t -> t.getButton() == button)
                .findFirst();

        // check if tile is present in tiles list
        if (!tileOptional.isPresent()) {
            JOptionPane.showMessageDialog(view,
                    "Tile information wasn't found!");
            return;
        }

        Tile tile = tileOptional.get();
        TileType tileType = TileType.fromIndex(
                view.getSelectedLevelTypeIndex());

        // check if index exists
        if (tileType == null) {
            JOptionPane.showMessageDialog(view,
                    "Selected level type is invalid!");
            return;
        }

        // update tile and button
        tile.setTileType(tileType);
        button.setText(tileType.getAbbreviation());
        button.setForeground(tileType.getForegroundColor());
        button.setBackground(tileType.getBackgroundColor());
    }
}
