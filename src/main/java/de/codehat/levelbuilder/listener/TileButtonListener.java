package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.Model;
import de.codehat.levelbuilder.View;
import de.codehat.levelbuilder.model.EnemyMovementType;
import de.codehat.levelbuilder.model.Tile;
import de.codehat.levelbuilder.model.TileType;

import javax.swing.*;
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
     * The model instance.
     */
    private Model model;

    /**
     * Row position of the listener's button.
     */
    private int row;

    /**
     * Col position of the listener's button.
     */
    private int col;

    /**
     * Creates a new listener for the tile buttons.
     *
     * @param view the view instance
     * @param model the model instance
     * @param row the row position of the listener's button
     * @param col the col position of the listener's button
     */
    public TileButtonListener(final View view, final Model model,
                              final int row, final int col) {
        this.view = view;
        this.model = model;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        Tile tile = model.getTiles()[row][col];

        TileType tileType = TileType.fromIndex(
                view.getSelectedLevelTypeIndex());

        // check if index exists
        if (tileType == null) {
            JOptionPane.showMessageDialog(view,
                    "Selected level type is invalid!");
            return;
        }

        // check for special tile types
        if (tileType == TileType.FOX) {
            askForMovementTypeAndSet(tile);
        } else {
            tile.setEnemyMovementType(null); // Reset enemy movement type
        }

        // update tile and buttons
        tile.setType(tileType);
        view.updateTileButtons(model);
    }

    /**
     * Asks the user, which movement type the tile should get and sets it.
     *
     * @param tile the concerning tile
     */
    private void askForMovementTypeAndSet(Tile tile) {
        JComboBox<String> cbEnemyMovementTypes = new JComboBox<>(EnemyMovementType.TYPES);
        cbEnemyMovementTypes.setName("cbEnemyMovementTypes");

        JOptionPane.showMessageDialog( null,
                cbEnemyMovementTypes,
                "Please select an enemy movement type",
                JOptionPane.QUESTION_MESSAGE);

        EnemyMovementType enemyMovementType = EnemyMovementType.fromIndex(cbEnemyMovementTypes.getSelectedIndex());
        tile.setEnemyMovementType(enemyMovementType);
    }
}
