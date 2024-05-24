package ca.ubc.cs304.ui.Crop;

import ca.ubc.cs304.ui.MakeGridHelper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.database.DatabaseConnectionHandler;

public class CropDetails implements ActionListener  {
    private String[] stringFields;
    private JCheckBox[] checkBoxes;

    private int numPairs;
    private JFrame frame = null;
    JFrame jFrame;
    JPanel jPanel;
    JButton nextButton;

    JButton crop_names;
    JButton crop_name_status;
    JButton crop_name_quantity;
    JButton crop_name_start_date;
    JButton crop_start_date_quantity;
    JButton crop_start_date_status;
    JButton crop_start_date_status_quantity;
    JButton buttonBack;
    DatabaseConnectionHandler dbHandler;
    String  fid;
    public CropDetails(DatabaseConnectionHandler dbHandler, String fid) {
        this.dbHandler = dbHandler;
        this.fid = fid;
        showFrame();

//        jFrame = new JFrame("Crop Details");
//
//        jFrame.setLocation(70,70);
//        jFrame.setSize(700,700);
//        jFrame.setLayout(null);
//        addPanelandButtons();
//        jFrame.setVisible(true);
    }
    public void showFrame() {
        // [Include the implementation of createAndShowGUI from SpringForm.java here]

        String[] labels = {
                "Status: ",
                "Quantity: ",
                "Start Date"
        };

        numPairs = labels.length;
        stringFields = new String[numPairs];
        checkBoxes = new JCheckBox[numPairs];

        JPanel p = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel title = new JLabel(labels[i], JLabel.TRAILING);
            p.add(title);
            checkBoxes[i]= new JCheckBox("");
            p.add(checkBoxes[i]);
        }
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        p.add(nextButton);
        p.add(new Label(""));

        buttonBack = new JButton("Back");
        buttonBack.addActionListener(this);
        p.add(buttonBack);
        p.add(new Label(""));

        MakeGridHelper gridhelper = new MakeGridHelper();
        gridhelper.makeCompactGrid(p, numPairs + 2, 2, 6, 6, 6, 6);


        JPanel mainPanel = new JPanel(new BorderLayout());
        Border border = new EmptyBorder(10,10,10,10);
        mainPanel.setBorder(border);

        JLabel mainLabel = new JLabel("Check the following value to display", JLabel.CENTER);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 13)); // Set the font, style, and size of the label

        // Add the label and panel p to mainPanel
        mainPanel.add(mainLabel, BorderLayout.NORTH); // Adds the label at the top
        mainPanel.add(p, BorderLayout.CENTER);


        frame = new JFrame("Supervisor Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setOpaque(true);
        frame.setContentPane(mainPanel); // Set the mainPanel as the content pane
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();

        if(e.getSource() == buttonBack){
            CropWindow win = new CropWindow(dbHandler,fid);
        }
        if(e.getSource() == nextButton){
            if(checkBoxes[0].isSelected() & checkBoxes[1].isSelected() & checkBoxes[2].isSelected()){
                Crop_startDate_status_quantity cssq = new Crop_startDate_status_quantity(dbHandler,fid);
            }
            else if(checkBoxes[2].isSelected() & checkBoxes[1].isSelected()){
                Crop_startDate_quantity csq = new Crop_startDate_quantity(dbHandler,fid);

            }
            else if(checkBoxes[2].isSelected() & checkBoxes[0].isSelected()){
                Crop_startDate_status css = new Crop_startDate_status(dbHandler,fid);

            }
            else if(checkBoxes[2].isSelected() ){
                Crop_name_startDate cns = new Crop_name_startDate(dbHandler,fid);

            }
            else if(checkBoxes[0].isSelected()){
                Crop_name_Status cns = new Crop_name_Status(dbHandler,fid);

            }
            else if(checkBoxes[1].isSelected()){
                Crop_name_quantity cnq = new Crop_name_quantity(dbHandler,fid);

            }
            else if(checkBoxes[0].isSelected()){
                Crop_name_Status cns = new Crop_name_Status(dbHandler,fid);

            }
            else {
                CropNames cn = new CropNames(dbHandler,fid);

            }

        }
    }
}