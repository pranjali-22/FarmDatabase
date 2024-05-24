package ca.ubc.cs304.ui.Insert;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.ProduceModel;
import ca.ubc.cs304.ui.Crop.*;
import ca.ubc.cs304.ui.MakeGridHelper;
import ca.ubc.cs304.ui.Supervisor.SupervisorWindow;
import ca.ubc.cs304.util.InputValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;
// TODO: add a window pop up : successfully added
// TODO: error crop not present in database

public class InsertCropWindow implements ActionListener {
    private JTextField[] textFields;
    private int numPairs;
    private JFrame frame = null;
    DatabaseConnectionHandler dbHandler;
    String fid;
    JFrame jFrame;
    JPanel jPanel;
    JLabel crop_name;
    JLabel start_month;
    JLabel start_year;
    JLabel quantity;
    JTextField cn;
    JTextField sm;
    JTextField sy;
    JTextField q;
    JButton insertCrop;
    JButton buttonBack;
    JButton buttonInsert;

    public InsertCropWindow(DatabaseConnectionHandler dbHandler, String fid) {
        this.fid = fid;
        this.dbHandler = dbHandler;
        showFrame();

    }

    public void showFrame() {
        // [Include the implementation of createAndShowGUI from SpringForm.java here]
        String[] labels = {"Enter Crop Name",
                "Enter Start Month",
                "Enter Start Year",
                "Enter Quantity"};

        numPairs = labels.length;
        textFields = new JTextField[numPairs];

        JPanel p = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            textFields[i] = new JTextField(10);
            l.setLabelFor(textFields[i]);
            p.add(textFields[i]);
        }
        buttonInsert = new JButton("Insert");
        buttonInsert.addActionListener(this);

        p.add(buttonInsert);
        p.add(new Label(""));
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(this);
        p.add(buttonBack);
        p.add(new Label(""));

        MakeGridHelper gridhelper = new MakeGridHelper();
        gridhelper.makeCompactGrid(p, numPairs + 2, 2, 6, 6, 6, 6);

        frame = new JFrame("Insert Crop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setOpaque(true);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (e.getSource() == buttonInsert) {
//            if ((int) (textFields[0].getText()) != textFields[0].getText()) {
            if(textFields[0].getText().contains("1") | textFields[0].getText().contains("2") | textFields[0].getText().contains("3")|
                    textFields[0].getText().contains("4") | textFields[0].getText().contains("5") |
                    textFields[0].getText().contains("6") |textFields[0].getText().contains("7") | textFields[0].getText().contains("8") | textFields[0].getText().contains("9")|
                    textFields[0].getText().contains("@") | textFields[0].getText().contains("!") |
                    textFields[0].getText().contains("#")|textFields[0].getText().contains("$") | textFields[0].getText().contains("%") | textFields[0].getText().contains("^")|
                    textFields[0].getText().contains("&") | textFields[0].getText().contains("*") |
                    textFields[0].getText().contains("(") |textFields[0].getText().contains(")") | textFields[0].getText().contains("?") | textFields[0].getText().contains(">")|
                    textFields[0].getText().contains("<") | textFields[0].getText().contains(":")
            ) {
                JOptionPane.showMessageDialog(null, "Crop Name should be string");
            }
            if(textFields[1].getText().contains("1") | textFields[1].getText().contains("2") | textFields[1].getText().contains("3")|
                    textFields[1].getText().contains("4") | textFields[1].getText().contains("5") |
                    textFields[1].getText().contains("6") |textFields[1].getText().contains("7") | textFields[1].getText().contains("8") | textFields[1].getText().contains("9")|
                    textFields[1].getText().contains("@") | textFields[1].getText().contains("!") |
                    textFields[1].getText().contains("#")|textFields[1].getText().contains("$") | textFields[1].getText().contains("%") | textFields[1].getText().contains("^")|
                    textFields[1].getText().contains("&") | textFields[1].getText().contains("*") |
                    textFields[1].getText().contains("(") |textFields[1].getText().contains(")") | textFields[1].getText().contains("?") | textFields[1].getText().contains(">")|
                    textFields[1].getText().contains("<") | textFields[1].getText().contains(":")
            ) {
                JOptionPane.showMessageDialog(null, "Month should be string");
            }

            for (int i = 0; i < numPairs; i++) {
                if (!InputValidationUtil.isValidInput(textFields[i].getText())) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                ProduceModel model = new ProduceModel(textFields[0].getText(), fid, textFields[1].getText(), Integer.parseInt(textFields[2].getText()), Integer.parseInt(textFields[3].getText()));
                dbHandler.insertProduceUser(model);
                for (int i = 0; i < numPairs; i++) {
                    textFields[i].setText("");
                }
//                JOptionPane.showMessageDialog(null, model.getCrop_name() + " Inserted", "Message Box", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if (e.getSource() == buttonBack) {
            frame.dispose();
            CropWindow win = new CropWindow(dbHandler, fid);
        }
    }
}



