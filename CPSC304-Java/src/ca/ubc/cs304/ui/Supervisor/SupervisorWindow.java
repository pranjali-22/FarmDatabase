package ca.ubc.cs304.ui.Supervisor;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropWindow;
import ca.ubc.cs304.ui.Farm.FarmDetails;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// shows button - your profile, farm, crop

// TODO : update the actionListener
public class SupervisorWindow implements ActionListener {
    String sid;
    private DatabaseConnectionHandler dbHandler;
    JFrame jFrame;
    JButton yourProfile;
    JButton farm;
    JButton crop;
    JButton backButton;



    public SupervisorWindow(String sid, DatabaseConnectionHandler dbHandler) {
        this.sid = sid;
        this.dbHandler = dbHandler;
        jFrame = new JFrame("Supervisor");

        Container container = jFrame.getContentPane();
        addPanelandButtons(container);

        jFrame.setLocationRelativeTo(null); // Center the window
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
    public void addPanelandButtons(Container container) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        // Set a border around the contentPanel to act as a margin
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, left, bottom, right margins

        yourProfile = new JButton("Your Profile");
        farm = new JButton("Farm");
        crop = new JButton("Crop");
        backButton = new JButton("Back");

        farm.addActionListener(this);
        yourProfile.addActionListener(this);
        crop.addActionListener(this);
        backButton.addActionListener(this);

        contentPanel.add(yourProfile);
        contentPanel.add(farm);
        contentPanel.add(crop);
        contentPanel.add(backButton);

        container.add(contentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrame.dispose();
        if(e.getSource()==yourProfile) {
            SupervisorDetails sd = new SupervisorDetails(dbHandler,sid);
        } else if(e.getSource() == farm) {
            FarmDetails fd = new FarmDetails(dbHandler, sid);
        } else if (e.getSource() ==crop) {
            CropWindow cw = new CropWindow(dbHandler,dbHandler.getMonitoredFarms(sid).getFarm_id());
        } else if (e.getSource() == backButton) {
            SupervisorLogin win = new SupervisorLogin(dbHandler);
        }

    }
}
