package ca.ubc.cs304.ui.Farmer;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
     take supervisor id
     search for that id in the database
     opens to a new window
    */

public class FarmerLogin implements ActionListener {
    JFrame jFrame;
    JLabel jLabel;
    JPanel jPanel;
    JButton loginButton;
    JTextField jTextField;
    String farmer_id;
    DatabaseConnectionHandler dbHandler;

    public FarmerLogin(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
        jFrame = new JFrame("Supervisor Login");
        jFrame.setLocation(70,70);
        jFrame.setSize(700,700);
        jFrame.setLayout(null);

        addPanelandButtons();
        jFrame.setVisible(true);

    }
    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setBounds(20, 90, 450, 450);


        jLabel = new JLabel("Enter ID");
        jLabel.setSize(700, 50);

        jTextField = new JTextField();
        jTextField.setBounds(20,20,450,50);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        jFrame.add(jTextField);

        jPanel.add(jLabel);
        jPanel.add(loginButton);
        jFrame.add(jPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            farmer_id = jTextField.getText();
            FarmerWindow sw = new FarmerWindow(dbHandler,farmer_id,(dbHandler.getWorkingFarmers(farmer_id)).getFarm_id());
        }
    }


}