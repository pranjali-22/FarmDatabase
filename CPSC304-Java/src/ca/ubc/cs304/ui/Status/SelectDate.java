package ca.ubc.cs304.ui.Status;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;

public class SelectDate{
    JFrame jFrame;
    JPanel jPanel;
    String month;
    int year;
    DatabaseConnectionHandler dbHandler;

    public SelectDate(DatabaseConnectionHandler dbHandler, String month, int  year) {
        this.month = month;
        this.year = year;
        this.dbHandler = dbHandler;
        jFrame = new JFrame("Select Date Window");

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
        JLabel title3 = new JLabel("Status");

        gridPanel.add(title1);
        gridPanel.add(title3);

        // Data
        String[] crop_names = dbHandler.selectDate(month,year);

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