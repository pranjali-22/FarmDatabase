package ca.ubc.cs304.ui.Status;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;

public class JoinStatusQuantity{
    JFrame jFrame;
    JPanel jPanel;
    String status;
    String fid;
    DatabaseConnectionHandler dbHandler;

    public JoinStatusQuantity(DatabaseConnectionHandler dbHandler, String status, String fid) {
        this.status = status;
        this.fid = fid;
        this.dbHandler = dbHandler;

        jFrame = new JFrame("Join Status Quantity Window");

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
        String[] crop_names = dbHandler.getJoinStatusQuantity(status,fid);

        for (String s : crop_names) {
            gridPanel.add(new JLabel(s));
        }

        container.add(gridPanel, BorderLayout.CENTER); // Add the grid panel to the center


        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            jFrame.dispose();
        });
        container.add(backButton, BorderLayout.SOUTH); // Add the Back button to the bottom
    }


}
