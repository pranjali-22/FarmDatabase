package ca.ubc.cs304.ui.OldGUI;


import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Supervisor.SupervisorLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FirstWindow implements ActionListener {
    JFrame jFrame;
    JPanel jPanel;
    JLabel jLabel;
    JButton farmerButton;
    JButton supervisorButton;
    DatabaseConnectionHandler dbHandler;
    public FirstWindow(DatabaseConnectionHandler dbHandler) {
        // TODO: add action listener to farm and supervisor buttons so that they open to farmWindow and
        //  supervisior window respectively
        jFrame = new JFrame("Natural's Farming Company");


        jFrame.setLocation(70,70);
        jFrame.setSize(700,700);
        jFrame.setLayout(null);
        this.dbHandler = dbHandler;


        addPanelandButtons();
        jFrame.setVisible(true);
    }
    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setBounds(20, 90, 450, 450);


        jLabel = new JLabel("Natural's Farming Company");
        jLabel.setSize(700, 50);


        farmerButton = new JButton("Farmer");
        farmerButton.setSize(50,50);
        supervisorButton = new JButton("Supervisor");
        supervisorButton.setSize(50,50);
        supervisorButton.addActionListener(this);


//        jPanel.add(jLabel);
        jPanel.add(farmerButton);
        jPanel.add(supervisorButton);
        jFrame.add(jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == supervisorButton) {
            SupervisorLogin sl = new SupervisorLogin(dbHandler);
        }

    }
}

