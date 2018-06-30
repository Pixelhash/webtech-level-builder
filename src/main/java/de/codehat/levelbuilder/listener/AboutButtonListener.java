package de.codehat.levelbuilder.listener;

import de.codehat.levelbuilder.Controller;
import de.codehat.levelbuilder.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Executes if the "About" button is pressed.
 * Allows you to view additional information about the program.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class AboutButtonListener implements ActionListener {

    /**
     * The view instance.
     */
    private View view;

    /**
     * Creates a new {@link AboutButtonListener}.
     * The view is needed for the dialog.
     *
     * @param view the view instance
     */
    public AboutButtonListener(final View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String message = "<html><p style='text-align: center;'>" + Controller.NAME + " Version " + Controller.VERSION + "<br><br>"
                + "Licensed under GNU Licence Version 3.<br>"
                + "Created by Marc-Niclas H. (CodeHat)."
                + "</p></html>";

        JOptionPane optionPane = new JOptionPane(new JLabel(message, JLabel.CENTER));
        JDialog dialog = optionPane.createDialog("About");
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}
