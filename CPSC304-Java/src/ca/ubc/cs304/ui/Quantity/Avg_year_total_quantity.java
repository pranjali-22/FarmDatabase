package ca.ubc.cs304.ui.Quantity;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropDetails;

import javax.swing.*;
import java.awt.*;

// displays the name of all the crops grown in the farm
public class Avg_year_total_quantity {
    JFrame jFrame;
    JPanel jPanel;
    String fid;
    DatabaseConnectionHandler dbHandler;

    public Avg_year_total_quantity(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        jFrame = new JFrame("Total Quantity Window");

        JPanel panel = new JPanel();
        addPanelandButtons(panel);
        jFrame.setContentPane(panel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void addPanelandButtons(JPanel container) {

        container.setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 20)); // Grid for the data and titles
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title1 = new JLabel("Crop Name");
        JLabel title5 = new JLabel("Quantity");

        gridPanel.add(title1);
        gridPanel.add(title5);

        // Data
        String[] crop_names = dbHandler.getAvg_year_total_quantity(fid);

        for (String s : crop_names) {
            gridPanel.add(new JLabel(s));
        }

        container.add(gridPanel, BorderLayout.CENTER); // Add the grid panel to the center

        // Back button at the bottom
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            jFrame.dispose();
            new QuantityWindow(dbHandler, fid);
        });
        container.add(backButton, BorderLayout.SOUTH); // Add the Back button to the bottom
    }


}
