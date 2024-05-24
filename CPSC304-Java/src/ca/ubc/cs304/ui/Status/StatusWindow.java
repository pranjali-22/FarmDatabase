package ca.ubc.cs304.ui.Status;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusWindow implements ActionListener {
    JFrame jFrame;
    JPanel mainPanel;
    JLabel cropStatusLabel, cropMonthLabel, cropYearLabel, quantityStatusLabel;
    JTextField yearField;
    JButton displayStatusButton, displayMonthYearButton, displayQuantityButton, backButton;
    JComboBox<String> statusComboBox, monthComboBox, statusComboBox2;
    DatabaseConnectionHandler dbHandler;
    String fid;

    public StatusWindow(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        jFrame = new JFrame("Status Window");
        addPanelandButtons();

        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null); // Center the window
        jFrame.setVisible(true);
    }

    public void addPanelandButtons() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        String[] statuses = { "sowing", "irrigation", "fertilizer", "harvested" };
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

        // Row 1: Crops with status
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cropStatusLabel = new JLabel("Crops with status");
        statusComboBox = new JComboBox<>(statuses);
        displayStatusButton = new JButton("Display");
        row1.add(cropStatusLabel);
        row1.add(statusComboBox);
        row1.add(displayStatusButton);

        // Row 2: Crops with month and year
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cropMonthLabel = new JLabel("Crops with month");
        monthComboBox = new JComboBox<>(months);
        cropYearLabel = new JLabel("and year");
        yearField = new JTextField(10); // Sizing the text field
        displayMonthYearButton = new JButton("Display");
        row2.add(cropMonthLabel);
        row2.add(monthComboBox);
        row2.add(cropYearLabel);
        row2.add(yearField);
        row2.add(displayMonthYearButton);

        // Row 3: Crop quantity with status
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityStatusLabel = new JLabel("Crop quantity with status");
        statusComboBox2 = new JComboBox<>(statuses);
        displayQuantityButton = new JButton("Display");
        row3.add(quantityStatusLabel);
        row3.add(statusComboBox2);
        row3.add(displayQuantityButton);

        JPanel row4 = new JPanel();
        backButton = new JButton("Back");
        row4.add(backButton);

        // Add action listeners
        displayStatusButton.addActionListener(this);
        displayMonthYearButton.addActionListener(this);
        displayQuantityButton.addActionListener(this);
        backButton.addActionListener(this);

        // Add rows to main panel
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row4);

        // Finally, add the main panel to the JFrame
        jFrame.add(mainPanel, BorderLayout.NORTH); // Using BorderLayout to keep it at the top
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == displayStatusButton) {
            String status = (String) statusComboBox.getSelectedItem();
            SelectStatus ss = new SelectStatus(dbHandler,status,fid);
        }
        if(e.getSource() == displayMonthYearButton) {
            String month = (String) monthComboBox.getSelectedItem();
            int year = Integer.parseInt(yearField.getText());
            yearField.setText("");
            SelectDate ss = new SelectDate(dbHandler,month,year);
        }
        if(e.getSource() == displayQuantityButton) {
            String status = (String) statusComboBox2.getSelectedItem();
            JoinStatusQuantity jsq = new JoinStatusQuantity(dbHandler,status,fid);
        }
        if(e.getSource() ==backButton){
            jFrame.dispose();
            CropWindow win= new CropWindow(dbHandler,fid);
        }
    }
}