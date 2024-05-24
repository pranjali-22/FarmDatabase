package ca.ubc.cs304.ui.Crop;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;

// displays the name of all the crops grown in the farm
public class Crop_name_startDate {
    JFrame jFrame;
    JPanel jPanel;
    String fid;
    DatabaseConnectionHandler dbHandler;


    public Crop_name_startDate(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        jFrame = new JFrame("Crop Start Date Window");

        JPanel panel = new JPanel();
        addPanelandButtons(panel);
        jFrame.setContentPane(panel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void addPanelandButtons(JPanel container) {
        container.setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 20)); // Grid for the data and titles
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titles
//        gridPanel.add(new JLabel("Crop Name"));
//        gridPanel.add(new JLabel("Start Month"));
//        gridPanel.add(new JLabel("Start Year"));

        // Data
        String[] crop_names = dbHandler.getCrop_name_startDate(fid);
        for (String s : crop_names) {
            gridPanel.add(new JLabel(s));
        }

        container.add(gridPanel, BorderLayout.CENTER); // Add the grid panel to the center

        // Back button at the bottom
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            jFrame.dispose();
            new CropDetails(dbHandler, fid);
        });
        container.add(backButton, BorderLayout.SOUTH); // Add the Back button to the bottom
    }
}