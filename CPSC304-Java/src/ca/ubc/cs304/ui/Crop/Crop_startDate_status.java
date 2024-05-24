package ca.ubc.cs304.ui.Crop;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;

// displays the name of all the crops grown in the farm
public class Crop_startDate_status{
    JFrame jFrame;
    JPanel jPanel;
    String fid;
    DatabaseConnectionHandler dbHandler;


    public Crop_startDate_status(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        jFrame = new JFrame("Crop StartDate Status Window");

        JPanel panel = new JPanel();
        addPanelandButtons(panel);
        jFrame.setContentPane(panel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void addPanelandButtons(JPanel container) {

        container.setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure

        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 10, 20)); // Grid for the data and titles
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title1 = new JLabel("Crop Name");
        JLabel title2 = new JLabel("Start Month");
        JLabel title3 = new JLabel("Start Year");
        JLabel title4 = new JLabel("Status");

//        gridPanel.add(title1);
//        gridPanel.add(title2);
//        gridPanel.add(title3);
//        gridPanel.add(title4);


        // Data
        String[] crop_names = dbHandler.getCrop_startDate_status(fid);

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