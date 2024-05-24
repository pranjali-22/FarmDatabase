package ca.ubc.cs304.ui.Crop;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;

// displays the name of all the crops grown in the farm
public class CropNames {
    JFrame jFrame;
    JPanel jPanel;
    String fid;
    DatabaseConnectionHandler dbHandler;

    public CropNames(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        jFrame = new JFrame("Crop Window");
        jFrame.setLocation(70, 70);
        jFrame.setSize(700, 700);
        jFrame.setLayout(null);
        addPanelandButtons();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setBounds(20, 90, 450, 450);
        String[] crop_names = dbHandler.getCropNames(fid);
        for(String s : crop_names) {
            JLabel jLabel = new JLabel(s);
            jPanel.add(jLabel);
        }
        jFrame.add(jPanel);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e ->{
            jFrame.dispose();
            new CropDetails(dbHandler,fid);
        });
        jFrame.add(backButton);

    }

}
