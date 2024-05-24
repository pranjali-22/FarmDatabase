package ca.ubc.cs304.ui.Crop;

import ca.ubc.cs304.ui.Delete.DeleteCropWindow;
import ca.ubc.cs304.ui.Insert.InsertCropWindow;
import ca.ubc.cs304.ui.Status.StatusWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Quantity.QuantityWindow;
import ca.ubc.cs304.ui.Supervisor.SupervisorLogin;

public class CropWindow implements ActionListener {
    JFrame frame;
    JButton cropDetailsButton, cropQuantityButton, cropStatusButton, insertButton, deleteButton, backButton;
    String fid, sid;
    DatabaseConnectionHandler dbHandler;

    public CropWindow(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;

        frame = new JFrame("Crop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        cropDetailsButton = new JButton("Crop Details");
        cropQuantityButton = new JButton("Quantity");
        cropStatusButton = new JButton("Status");
        insertButton = new JButton("Insert Crop");
        deleteButton = new JButton("Delete Crop");
        backButton = new JButton("Back");

        cropDetailsButton.addActionListener(this);
        cropQuantityButton.addActionListener(this);
        cropStatusButton.addActionListener(this);
        insertButton.addActionListener(this);
        deleteButton.addActionListener(this);
        backButton.addActionListener(this);

        contentPanel.add(cropDetailsButton);
        contentPanel.add(cropQuantityButton);
        contentPanel.add(cropStatusButton);
        contentPanel.add(insertButton);
        contentPanel.add(deleteButton);
        contentPanel.add(backButton);

        frame.add(contentPanel);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new SupervisorLogin(dbHandler);
        } else {
            frame.dispose();
            if (e.getSource() == cropDetailsButton) {
                new CropDetails(dbHandler, fid);
            } else if (e.getSource() == cropQuantityButton) {
                new QuantityWindow(dbHandler, fid);
            } else if (e.getSource() == cropStatusButton) {
                new StatusWindow(dbHandler, fid);
            } else if (e.getSource() == insertButton) {
                new InsertCropWindow(dbHandler, fid);
            } else if (e.getSource() == deleteButton) {
                new DeleteCropWindow(dbHandler, fid);
            }
        }
    }
}
