package de.codehat.levelbuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportButtonListener implements ActionListener {

    private View view;

    public ExportButtonListener(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Level level = new Level();

        long goals = view.getTiles().stream()
                .map(tile -> tile.levelType)
                .filter(levelType -> levelType == LevelType.GOAL)
                .count();

        if (goals == 0) {
            JOptionPane.showMessageDialog(view, "At least one goal is required!");
            return;
        }

        // convert list to array
        Tile[] tiles = new Tile[view.getTiles().size()];
        tiles = view.getTiles().toArray(tiles);

        // convert time to int
        String timeString = view.getSpLevelTime().getValue().toString();
        int time = Integer.parseInt(timeString.trim());

        level.possibleGoals = Math.toIntExact(goals);
        level.name = view.getTfLevelName().getText();
        level.nameClean = view.getTfLevelNameClean().getText();
        level.time = time;
        level.tiles = tiles;

        // build JSON string
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        final String levelJson = gson.toJson(level);

        // open save file dialog
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            FileWriter fw;
            try {
                fw = new FileWriter(file);
                fw.write(levelJson);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.err.println("Couldn't save file to " + file.getAbsolutePath());
                JOptionPane.showMessageDialog(view, "Unable to save file!");
                e.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(view, "Saved file to " + file.getAbsolutePath());
        }
    }
}
