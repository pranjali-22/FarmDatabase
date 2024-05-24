package ca.ubc.cs304.ui.OldGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FarmerLogin implements ActionListener {
    JFrame jFrame;
    JLabel jLabel;
    JPanel jPanel;
    JButton loginButton;
    JTextField jTextField;
    public FarmerLogin() {
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
//        jTextField.setFont("", Font.PLAIN,40);
        jTextField.setBounds(20,20,450,50);
        jTextField.setEditable(false);

        loginButton = new JButton("Login");

        jFrame.add(jTextField);

        jPanel.add(jLabel);
        jPanel.add(loginButton);
        jFrame.add(jPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
