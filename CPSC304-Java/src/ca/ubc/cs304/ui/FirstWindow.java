package ca.ubc.cs304.ui;

import java.awt.Dimension;
import ca.ubc.cs304.ui.Crop.AllCrops;
import ca.ubc.cs304.ui.Crop.AllFarms;
import ca.ubc.cs304.ui.Crop.FarmsWithAllCrops;
import ca.ubc.cs304.ui.Farmer.FarmerLogin;
import ca.ubc.cs304.ui.Supervisor.SupervisorLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.database.DatabaseConnectionHandler;

// displays the buttons farmer, supervisor, crop and farm
// add actionlistener to all the buttons
public class FirstWindow implements ActionListener  {
    private JFrame frame;
    JPanel jPanel;
    JLabel jLabel;
    JButton farmerButton;
    JButton supervisorButton;
    JButton allCrops;
    JButton allfarms;
    JButton farmsWithAllCrops;
    JButton exitButton;
    DatabaseConnectionHandler dbHandler;
    public FirstWindow(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
        frame = new JFrame("Agriculture Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        addPanelandButtons(container);

        Dimension d =  Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle r = frame.getBounds();
        frame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void addPanelandButtons(Container container) {

        farmerButton = new JButton("Farmer");
        supervisorButton = new JButton("Supervisor");
        allCrops = new JButton("Company Crops");
        allfarms = new JButton("Company Farms");
        farmsWithAllCrops = new JButton("Farms with All Crops");
        exitButton = new JButton("Exit");

        supervisorButton.addActionListener(this);
        farmerButton.addActionListener(this);
        allCrops.addActionListener(this);
        allfarms.addActionListener(this);
        farmsWithAllCrops.addActionListener(this);
        exitButton.addActionListener(this);


        container.add(farmerButton);
        container.add(supervisorButton);
        container.add(allCrops);
        container.add(allfarms);
        container.add(farmsWithAllCrops);

        container.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        if(e.getSource() == supervisorButton) {
            SupervisorLogin sl = new SupervisorLogin(dbHandler);
        }
        if(e.getSource() == farmerButton) {
            FarmerLogin fl = new FarmerLogin(dbHandler);
        }
        if(e.getSource() == allCrops) {
            AllCrops af = new AllCrops(dbHandler);
        }
        if(e.getSource() == allfarms) {
            AllFarms af = new AllFarms(dbHandler);
        }
        if(e.getSource() == farmsWithAllCrops) {
            FarmsWithAllCrops fwc = new FarmsWithAllCrops(dbHandler);
        }
    }
}