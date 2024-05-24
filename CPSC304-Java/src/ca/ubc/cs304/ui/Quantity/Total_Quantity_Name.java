package ca.ubc.cs304.ui.Quantity;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropDetails;

import javax.swing.*;
import java.awt.*;

// displays the name of all the crops grown in the farm
public class Total_Quantity_Name{
    JFrame jFrame;
    JPanel jPanel;
    String fid;
    DatabaseConnectionHandler dbHandler;

    public Total_Quantity_Name(DatabaseConnectionHandler dbHandler, String fid) {
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

    public void addPanelandButtons(JPanel containter) {

        String[] crop_names = dbHandler.getTotal_Quantity(fid);
        containter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        containter.setLayout(new GridLayout(crop_names.length, 2, 10, 20)); // 10 is the vertical gap between rows
        JLabel title1 = new JLabel("Crop Name");
        JLabel title2 = new JLabel("Total Quantity");
        containter.add(title1);
        containter.add(title2);
        for(String s : crop_names) {
            JLabel jLabel = new JLabel(s);
            containter.add(jLabel);
        }
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e ->{
            jFrame.dispose();
            new QuantityWindow(dbHandler,fid);
        });
        containter.add(backButton);
    }

}
