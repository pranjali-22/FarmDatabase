package ca.ubc.cs304.ui.Quantity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropWindow;
import ca.ubc.cs304.util.InputValidationUtil;

public class QuantityWindow implements ActionListener  {
    JFrame jFrame;
    JPanel jPanel;
    JButton total_quantity_name;
    JButton total_quantity_year;
    JButton total_quantity_name_year;
    JButton insertButton,backButton;
    JTextField jTextField;
    JButton avg_year_total_quantity;
    DatabaseConnectionHandler dbHandler;
    String fid;

    public QuantityWindow(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        jFrame = new JFrame("Quantity Window");
        jFrame.setLocation(70,70);
        jFrame.setSize(700,700);
        jFrame.setLayout(null);
        addPanelandButtons();

        jFrame.setVisible(true);
    }
    public void addPanelandButtons() {
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.X_AXIS));
        jPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        total_quantity_name = new JButton("Total Quantity Of Each Crop");
        total_quantity_year = new JButton("Total Quantity In Each Year");
        total_quantity_name_year = new JButton("Total Quantity Of Each Crop In Year");
        avg_year_total_quantity = new JButton("Average Quantity Produced Per Year");

        JLabel havingQuntityLbl = new JLabel("Having Quantity: ");
        insertButton = new JButton("Query ");
        jTextField = new JTextField(10);

        total_quantity_name.addActionListener(this);
        total_quantity_year.addActionListener(this);
        total_quantity_name_year.addActionListener(this);
        insertButton.addActionListener(this);
        avg_year_total_quantity.addActionListener(this);


        jPanel.add(total_quantity_name);
        jPanel.add(total_quantity_year);
        jPanel.add(total_quantity_name_year);
        jPanel.add(avg_year_total_quantity);

        jPanel2.add(havingQuntityLbl);
        jPanel2.add(jTextField);
        jPanel2.add(insertButton);

        JPanel jPanel3 = new JPanel();
        backButton= new JButton("Back");
        backButton.addActionListener(this);
        jPanel3.add(backButton);



        JPanel mainpane = new JPanel(new BorderLayout());
        mainpane.add(jPanel,BorderLayout.NORTH);
        mainpane.add(jPanel2,BorderLayout.CENTER);
        mainpane.add(jPanel3,BorderLayout.SOUTH);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainpane.setOpaque(true);
        jFrame.setContentPane(mainpane); // Set the mainPanel as the content pane
        jFrame.pack();
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == total_quantity_name) {
            jFrame.dispose();
            Total_Quantity_Name tqn = new Total_Quantity_Name(dbHandler,fid);
        }
        if(e.getSource() == total_quantity_year) {
            jFrame.dispose();
            Total_Quantity_Year tqy = new Total_Quantity_Year(dbHandler,fid);
        }
        if(e.getSource() == total_quantity_name_year) {
            jFrame.dispose();
            Total_quantity_name_year tqny = new Total_quantity_name_year(dbHandler,fid);
        }
        if(e.getSource() == insertButton) {
            String txt = jTextField.getText();
            if(InputValidationUtil.isValidInput(txt)) {
                int q = Integer.parseInt(txt);
                jFrame.dispose();
                Having_total_quantity_name htqn = new Having_total_quantity_name(dbHandler, fid, q);
            }
        }
        if(e.getSource() == avg_year_total_quantity) {
            jFrame.dispose();
            Avg_year_total_quantity atq = new Avg_year_total_quantity(dbHandler,fid);
        }
        if(e.getSource() ==  backButton){
            jFrame.dispose();
            CropWindow win= new CropWindow(dbHandler,fid);
        }
    }
}