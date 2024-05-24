package ca.ubc.cs304.ui.Farmer;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.MonitoredFarmsModel;
import ca.ubc.cs304.model.WorkingFarmersModel;
//import ca.ubc.cs304.model.FarmAndSupervisorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// displays the name, sid, salary, phone number of the supervisor
// TODO: update supervisor details just in case (optional)
// TODO: change the model
public class FarmerWindow implements ActionListener {
    DatabaseConnectionHandler dbHandler;
    String farmer_id;
    String farm_id;
    JLabel farmer_name;
    JLabel farmer_phone_number;
    JLabel farmer_type;
    JLabel farmer_hours;
    JFrame jFrame;
    JPanel jPanel;
    JButton update;
    FarmerWindow(DatabaseConnectionHandler dbHandler, String farmer_id, String farm_id) {
        this.farmer_id = farmer_id;
        this.farm_id = farm_id;
        this.dbHandler = dbHandler;
        jFrame = new JFrame("Your Profile");
        jFrame.setLocation(70,70);
        jFrame.setSize(700,700);
        jFrame.setLayout(null);
        addPanelandButtons();
        jFrame.setVisible(true);

    }
    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setBounds(20, 90, 450, 450);

        WorkingFarmersModel b = dbHandler.getWorkingFarmers(farmer_id);


        farmer_name = new JLabel(b.getFarmer_name());
        farmer_phone_number = new JLabel(b.getFarmer_phone_number());
        farmer_hours = new JLabel(String.valueOf(b.getFarmer_hours()));
        farmer_type = new JLabel(b.getFarmer_type());


        farmer_name.setSize(700, 50);
        farmer_phone_number.setSize(700, 50);
        farmer_hours.setSize(700, 50);
        farmer_type.setSize(700, 50);


        update = new JButton("Update Details");
        update.addActionListener(this);


//        jPanel.add(fid);
        jPanel.add(farmer_name);
        jPanel.add(farmer_phone_number);
        jPanel.add(farmer_hours);
        jPanel.add(farmer_type);
        jPanel.add(update);

        jFrame.add(jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == update) {
            FarmerUpdate fu = new FarmerUpdate(dbHandler,farmer_id,farm_id);
        }
    }
}
