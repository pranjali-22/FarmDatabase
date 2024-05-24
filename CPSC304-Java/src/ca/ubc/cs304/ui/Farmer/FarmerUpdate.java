package ca.ubc.cs304.ui.Farmer;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.WorkingFarmersModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class FarmerUpdate implements ActionListener {
    DatabaseConnectionHandler dbHandler;
    String farmer_id;
    String farm_id;
    JFrame jFrame;
    JPanel jPanel;
    JLabel farmer_name;
    JLabel farmer_phone_number;
    JLabel farmer_type;
    JLabel farmer_hours;
    JTextField name;
    JTextField type;
    JTextField phone_number;
    JTextField hours;
    JButton updateDetails;
    WorkingFarmersModel model = null;

    public FarmerUpdate(DatabaseConnectionHandler dbHandler, String farmer_id, String farm_id) {
        this.farmer_id = farmer_id;
        this.farm_id = farm_id;
        this.dbHandler = dbHandler;
        jFrame = new JFrame("Update Crop");
        jFrame.setLocation(70, 70);
        jFrame.setSize(700, 700);
        jFrame.setLayout(null);
        addPanelandButtons();
        jFrame.setVisible(true);
    }

    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setBounds(20, 90, 450, 450);
        farmer_name = new JLabel("Enter Name");
        farmer_phone_number = new JLabel("Enter Phone Number");
        farmer_type = new JLabel("Enter Type");
        farmer_hours = new JLabel("Enter Hours");

        updateDetails = new JButton("Update Details");
        updateDetails.setBounds(20, 250, 20, 20);
        updateDetails.addActionListener(this);

        name = new JTextField();
        type = new JTextField();
        phone_number = new JTextField();
        hours = new JTextField();
        name.setBounds(20, 20, 50, 50);
        type.setBounds(20, 100, 50, 50);
        phone_number.setBounds(20, 170, 50, 50);
        hours.setBounds(20, 200, 50, 50);

        jFrame.add(name);
        jFrame.add(type);
        jFrame.add(phone_number);
        jFrame.add(hours);
        jPanel.add(farmer_name);
        jPanel.add(farmer_type);
        jPanel.add(farmer_phone_number);
        jPanel.add(farmer_hours);

        jPanel.add(updateDetails);

        jFrame.add(jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isvalid = true;


        if (e.getSource() == updateDetails) {
            if (farmer_name.getText().contains("1") | farmer_name.getText().contains("2") | farmer_name.getText().contains("3") |
                    farmer_name.getText().contains("4") | farmer_name.getText().contains("5") |
                    farmer_name.getText().contains("6") | farmer_name.getText().contains("7") | farmer_name.getText().contains("8") | farmer_name.getText().contains("9") |
                    farmer_name.getText().contains("@") | farmer_name.getText().contains("!") |
                    farmer_name.getText().contains("#") | farmer_name.getText().contains("$") | farmer_name.getText().contains("%") | farmer_name.getText().contains("^") |
                    farmer_name.getText().contains("&") | farmer_name.getText().contains("*") |
                    farmer_name.getText().contains("(") | farmer_name.getText().contains(")") | farmer_name.getText().contains("?") | farmer_name.getText().contains(">") |
                    farmer_name.getText().contains("<") | farmer_name.getText().contains(":")
            ) {
                isvalid = false;
                JOptionPane.showMessageDialog(null, "Farmer Name should be string");
            } else if (farmer_name.getText().contains("1") | farmer_type.getText().contains("2") | farmer_type.getText().contains("3") |
                    farmer_type.getText().contains("4") | farmer_type.getText().contains("5") |
                    farmer_type.getText().contains("6") | farmer_type.getText().contains("7") | farmer_type.getText().contains("8") | farmer_type.getText().contains("9") |
                    farmer_type.getText().contains("@") | farmer_type.getText().contains("!") |
                    farmer_type.getText().contains("#") | farmer_type.getText().contains("$") | farmer_type.getText().contains("%") | farmer_type.getText().contains("^") |
                    farmer_type.getText().contains("&") | farmer_type.getText().contains("*") |
                    farmer_type.getText().contains("(") | farmer_type.getText().contains(")") | farmer_type.getText().contains("?") | farmer_type.getText().contains(">") |
                    farmer_type.getText().contains("<") | farmer_type.getText().contains(":")
            ) {
                isvalid = false;
                JOptionPane.showMessageDialog(null, "Type should be string");
            } else {
                if (isvalid) {
                    model = new WorkingFarmersModel(farmer_id, name.getText(), phone_number.getText(), type.getText(), farmer_id, Float.parseFloat(hours.getText()));
                    dbHandler.updateFarmer(model);
                    JOptionPane.showMessageDialog(null, "Details Updated");
                }
            }
        }
    }
}
