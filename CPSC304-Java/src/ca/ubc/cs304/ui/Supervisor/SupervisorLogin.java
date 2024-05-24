package ca.ubc.cs304.ui.Supervisor;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.FirstWindow;
import ca.ubc.cs304.ui.MakeGridHelper;
import ca.ubc.cs304.util.InputValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
     take supervisor id
     search for that id in the database
     opens to a new window
    */

public class SupervisorLogin implements ActionListener {
    private JTextField[] textFields;
    private int numPairs;
    private JFrame frame =null;
    JFrame jFrame;
    JLabel jLabel;
    JPanel jPanel;
    JButton loginButton;
    JButton buttonBack;
    JTextField jTextField;
    String sid;
    DatabaseConnectionHandler dbHandler;

    public SupervisorLogin(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
        showFrame();

    }
//    public void makeCompactGrid(Container parent,
//                                int rows, int cols,
//                                int initialX, int initialY,
//                                int xPad, int yPad) {
//        // Implementation of makeCompactGrid from SpringUtilities.java
//        SpringLayout layout;
//        try {
//            layout = (SpringLayout) parent.getLayout();
//        } catch (ClassCastException exc) {
//            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
//            return;
//        }
//
//        Spring x = Spring.constant(initialX);
//        for (int c = 0; c < cols; c++) {
//            Spring width = Spring.constant(0);
//            for (int r = 0; r < rows; r++) {
//                width = Spring.max(width,
//                        getConstraintsForCell(r, c, parent, cols).getWidth());
//            }
//            for (int r = 0; r < rows; r++) {
//                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
//                constraints.setX(x);
//                constraints.setWidth(width);
//            }
//            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
//        }
//
//        Spring y = Spring.constant(initialY);
//        for (int r = 0; r < rows; r++) {
//            Spring height = Spring.constant(0);
//            for (int c = 0; c < cols; c++) {
//                height = Spring.max(height,
//                        getConstraintsForCell(r, c, parent, cols).getHeight());
//            }
//            for (int c = 0; c < cols; c++) {
//                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
//                constraints.setY(y);
//                constraints.setHeight(height);
//            }
//            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
//        }
//
//        SpringLayout.Constraints pCons = layout.getConstraints(parent);
//        pCons.setConstraint(SpringLayout.SOUTH, y);
//        pCons.setConstraint(SpringLayout.EAST, x);
//    }
//
//    private SpringLayout.Constraints getConstraintsForCell(int row, int col, Container parent, int cols) {
//        // Implementation of getConstraintsForCell from SpringUtilities.java
//        SpringLayout layout = (SpringLayout) parent.getLayout();
//        Component c = parent.getComponent(row * cols + col);
//        return layout.getConstraints(c);
//    }

    // Main application logic from the original SpringForm.java
    public void showFrame() {
        // [Include the implementation of createAndShowGUI from SpringForm.java here]
        String[] labels = {"Enter Supervisor id: "};
        numPairs = labels.length;
        textFields = new JTextField[numPairs];

        JPanel p = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            textFields[i] = new JTextField(10);
            l.setLabelFor(  textFields[i] );
            p.add(  textFields[i] );
        }
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        p.add(loginButton);
        p.add(new Label(""));
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(this);
        p.add(buttonBack);
        p.add(new Label(""));
        
        MakeGridHelper gridhelper = new MakeGridHelper();
        gridhelper.makeCompactGrid(p, numPairs+2, 2, 6, 6, 6, 6);

        frame = new JFrame("Supervisor Login");
        frame.setLocationRelativeTo(null); // Center the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setOpaque(true);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            sid = textFields[0] .getText();
            if(InputValidationUtil.isValidInput(sid)){
                frame.dispose();
                SupervisorWindow sw = new SupervisorWindow(sid, dbHandler);
            }
        }
        if(e.getSource() == buttonBack){
            frame.dispose();
            FirstWindow win = new FirstWindow(dbHandler);
        }
    }


}