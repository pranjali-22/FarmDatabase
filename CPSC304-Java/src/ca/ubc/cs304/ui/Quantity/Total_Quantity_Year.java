package ca.ubc.cs304.ui.Quantity;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropDetails;

import javax.swing.*;
import java.awt.*;

// displays the name of all the crops grown in the farm
public class Total_Quantity_Year {
    JFrame jFrame;
    JPanel jPanel;
    String fid;
    DatabaseConnectionHandler dbHandler;

    public Total_Quantity_Year(DatabaseConnectionHandler dbHandler, String fid) {
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

        JLabel title3 = new JLabel("Start Year");
        JLabel title5 = new JLabel("Quantity");

        gridPanel.add(title3);
        gridPanel.add(title5);

        // Data
        String[] crop_names = dbHandler.getTotal_Quantity_Year(fid);

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
