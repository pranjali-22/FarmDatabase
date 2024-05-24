package ca.ubc.cs304.ui.Crop;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;

// displays the name of all the crops grown in the farm
public class AllCrops {
    JFrame jFrame;
    JPanel jPanel;
    DatabaseConnectionHandler dbHandler;

    public AllCrops(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
        jFrame = new JFrame("Crop Window");
        jFrame.setLocation(70, 70);
        jFrame.setSize(700, 700);
        jFrame.setLayout(null);
        addPanelandButtons();
        jFrame.setVisible(true);
    }

    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setBounds(20, 90, 450, 450);
        String[] crop_names = dbHandler.getAllCropNames();
        for(String s : crop_names) {
            JLabel jLabel = new JLabel(s);
            jPanel.add(jLabel);
        }
        jFrame.add(jPanel);
    }

}
