package ca.ubc.cs304.ui.Farm;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.MonitoredFarmsModel;
import ca.ubc.cs304.ui.MakeGridHelper;
import ca.ubc.cs304.ui.Supervisor.SupervisorWindow;
//import ca.ubc.cs304.model.FarmAndSupervisorModel;

import javax.swing.*;
import java.awt.*;

// displays the area, location and farmID of the farm
// TODO: change the model
public class FarmDetails {
    DatabaseConnectionHandler dbHandler;
    private String[] stringFields;
    private int numPairs;
    private JFrame frame = null;
    JButton buttonBack;

    String sid;
    JLabel farm_id;
    JLabel location;
    JLabel area;
    JFrame jFrame;
    JPanel jPanel;

    public FarmDetails(DatabaseConnectionHandler dbHandler, String sid) {
        this.sid = sid;
        this.dbHandler = dbHandler;

        showFrame();

    }

    public void showFrame() {
        // [Include the implementation of createAndShowGUI from SpringForm.java here]
        String[] labels = {
                "Farm id: ",
                "Location: ",
                "Area: "
        };


        numPairs = labels.length;
        stringFields = new String[numPairs];
        MonitoredFarmsModel model = dbHandler.getMonitoredFarms(sid);

        stringFields[0] = model.getFarm_id();
        stringFields[1] = model.getLocation();
        stringFields[2] = String.valueOf(model.getArea_acre());

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

        frame = new JFrame("Farm Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setOpaque(true);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
    }
}
