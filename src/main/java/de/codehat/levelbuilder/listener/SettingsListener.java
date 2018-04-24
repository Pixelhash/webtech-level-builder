package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Executes if the "Settings" button is pressed.
 * Allows you to adjust some settings.
 *
 * @author Marc-Niclas H. (codehat)
 */
public final class SettingsListener implements ActionListener {

    /**
     * The view instance.
     */
    private View view;

    /**
     * Creates a new {@link SettingsListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *
     * @param view the view
     */
    public SettingsListener(final View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        view.askForRowsAndCols();
        view.generateTileButtons(true);
        view.revalidate();
        view.repaint();
        //view.pack();
        view.updateTitle();
    }
}
