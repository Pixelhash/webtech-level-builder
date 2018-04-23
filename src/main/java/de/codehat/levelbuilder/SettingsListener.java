package de.codehat.levelbuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Executes if the "Settings" button is pressed.
 * Allows you to adjust some settings.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class SettingsListener implements ActionListener {

    /**
     * the view instance
     */
    private View view;

    /**
     * Creates a new {@link SettingsListener}.
     * The view is needed to fetch all visual elements such as buttons.
     *
     * @param view the view
     */
    SettingsListener(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        view.askForRowsAndCols();
        view.generateTileButtons(true);
        view.revalidate();
        view.repaint();
        //view.pack();
        view.updateTitle();
    }
}
