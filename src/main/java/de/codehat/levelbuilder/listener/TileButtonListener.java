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
        } else if (tileType == TileType.SPEED_POWERUP) {
            askForSpeedPowerUpDetailsAndSet(tile);
        } else {
            tile.setEnemyMovementType(null); // Reset enemy movement type

            // Reset speed power-up details.
            tile.setAppearChance(null);
            tile.setTimeOnField(null);
            tile.setSpeedIncrease(null);
        }

        // update tile and buttons
        tile.setType(tileType);
        view.updateTileButtons(model);
    }

    /**
     * Asks the user for the details of the speed power-up.
     * E.g the appear chance or the speed increase.
     *
     * @param tile the concerning tile
     */
    private void askForSpeedPowerUpDetailsAndSet(final Tile tile) {

        // Create all JSpinner.
        JSpinner sAppearChance = new JSpinner(new SpinnerNumberModel(0.5, 0.01, 1.0, 0.01));
        JSpinner sTimeOnField = new JSpinner(new SpinnerNumberModel(5, 5, 20, 1));
        JSpinner sSpeedIncrease = new JSpinner(new SpinnerNumberModel(100, 50, 200, 5));

        // Set their names for the tests (if any).
        sAppearChance.setName("appearChanceSpinner");
        sTimeOnField.setName("timeOnFieldSpinner");
        sSpeedIncrease.setName("speedIncreaseSpinner");

        Object[] message = {
                "Appear Chance (0.01 - 1.0):", sAppearChance,
                "Time On Field (in seconds):", sTimeOnField,
                "Speed Increase (in milliseconds):", sSpeedIncrease
        };

        JOptionPane.showMessageDialog(this.view,
                message,
                "Please give additional information",
                JOptionPane.QUESTION_MESSAGE);

        tile.setAppearChance((double) sAppearChance.getValue());
        tile.setTimeOnField((int) sTimeOnField.getValue());
        tile.setSpeedIncrease((int) sSpeedIncrease.getValue());
    }

    /**
     * Asks the user, which movement type the tile should get and sets it.
     *
     * @param tile the concerning tile
     */
    private void askForMovementTypeAndSet(final Tile tile) {
        JComboBox<String> cbEnemyMovementTypes = new JComboBox<>(EnemyMovementType.TYPES);
        cbEnemyMovementTypes.setName("cbEnemyMovementTypes");

        JOptionPane.showMessageDialog( this.view,
                cbEnemyMovementTypes,
                "Please select an enemy movement type",
                JOptionPane.QUESTION_MESSAGE);

        EnemyMovementType enemyMovementType = EnemyMovementType.fromIndex(cbEnemyMovementTypes.getSelectedIndex());
        tile.setEnemyMovementType(enemyMovementType);
    }
}
