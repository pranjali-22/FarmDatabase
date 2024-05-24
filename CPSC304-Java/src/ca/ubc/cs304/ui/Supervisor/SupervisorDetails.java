package ca.ubc.cs304.ui.Supervisor;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.MonitoredFarmsModel;
import ca.ubc.cs304.ui.MakeGridHelper;
//import ca.ubc.cs304.model.FarmAndSupervisorModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// displays the name, sid, salary, phone number of the supervisor
// TODO: update supervisor details just in case (optional)
// TODO: change the model
public class SupervisorDetails {
    DatabaseConnectionHandler dbHandler;
    private String[] stringFields;
    private int numPairs;
    private JFrame frame = null;
    String sid;
    JButton buttonBack;


    SupervisorDetails(DatabaseConnectionHandler dbHandler, String sid) {
        this.sid = sid;
        this.dbHandler = dbHandler;
        showFrame();

    }

    public void showFrame() {
        // [Include the implementation of createAndShowGUI from SpringForm.java here]
        String[] labels = {
                "Supervisor id: ",
                "Supervisor Name: ",
                "Phone Number",
                "Salary"
        };

        numPairs = labels.length;
        stringFields = new String[numPairs];
        MonitoredFarmsModel model = dbHandler.getMonitoredFarms(sid);

        stringFields[0] = model.getSupervisor_id();
        stringFields[1] = model.getSupervisor_name();
        stringFields[2] = String.valueOf(model.getPhone_number());
        stringFields[3] = String.valueOf(model.getSalary());


        JPanel p = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel title = new JLabel(labels[i], JLabel.TRAILING);
            p.add(title);
            JLabel field = new JLabel(stringFields[i], JLabel.TRAILING);
            p.add(field);
        }

        buttonBack = new JButton("Back");
        buttonBack.addActionListener(event -> {
            frame.dispose();
            new SupervisorWindow(sid, dbHandler);
        });
        p.add(buttonBack);
        p.add(new Label(""));

        MakeGridHelper gridhelper = new MakeGridHelper();
        gridhelper.makeCompactGrid(p, numPairs + 1, 2, 6, 6, 6, 6);

        frame = new JFrame("Supervisor Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setOpaque(true);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
    }

//    public void addPanelandButtons() {
//        jPanel = new JPanel();
//        jPanel.setBounds(20, 90, 450, 450);
//
//        MonitoredFarmsModel b = dbHandler.getMonitoredFarms(sid);
//
//        supervisior_id = new JLabel(b.getSupervisor_id());
//        sname = new JLabel(b.getSupervisor_name());
//        sphone_number = new JLabel(String.valueOf(b.getPhone_number()));
//        salary = new JLabel(String.valueOf(b.getSalary()));
//
//        supervisior_id.setSize(700, 50);
//        sname.setSize(700, 50);
//        sphone_number.setSize(700, 50);
//        salary.setSize(700, 50);
//
//
//        jPanel.add(supervisior_id);
//        jPanel.add(sname);
//        jPanel.add(sphone_number);
//        jPanel.add(salary);
//
//        jFrame.add(jPanel);
//    }
}
