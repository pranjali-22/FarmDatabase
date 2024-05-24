package ca.ubc.cs304.ui.OldGUI;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.OldGUI.DatabaseGui;

import javax.swing.*;
import java.awt.*;

public class FarmDeleteWindow  extends JFrame {
    // Utility method from SpringUtilities.java
    private JTextField[] textFields;
    private int numPairs;
    private DatabaseConnectionHandler dbHandler = null;
    private JFrame frame =null;
    public void printSizes(Component c) {
        System.out.println("minimumSize = " + c.getMinimumSize());
        System.out.println("preferredSize = " + c.getPreferredSize());
        System.out.println("maximumSize = " + c.getMaximumSize());
    }

    public void makeGrid(Container parent,
                         int rows, int cols,
                         int initialX, int initialY,
                         int xPad, int yPad) {
        // Implementation of makeGrid from SpringUtilities.java
        // [Include the implementation of makeGrid here, adapted to be part of the SpringForm class]
    }

    public void makeCompactGrid(Container parent,
                                int rows, int cols,
                                int initialX, int initialY,
                                int xPad, int yPad) {
        // Implementation of makeCompactGrid from SpringUtilities.java
        SpringLayout layout;
        try {
            layout = (SpringLayout) parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }

        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                        getConstraintsForCell(r, c, parent, cols).getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                        getConstraintsForCell(r, c, parent, cols).getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }

    private SpringLayout.Constraints getConstraintsForCell(int row, int col, Container parent, int cols) {
        // Implementation of getConstraintsForCell from SpringUtilities.java
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    // Main application logic from the original SpringForm.java
    public void showFrame() {
        // [Include the implementation of createAndShowGUI from SpringForm.java here]
        String[] labels = {"Farm id: "};
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
        JButton button = new JButton("Delete Farm");
        button.addActionListener(e ->onButtonDelete());
        p.add(button);
        p.add(new Label(""));
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(e -> onButtonBack());
        p.add(buttonBack);
        p.add(new Label(""));

        makeCompactGrid(p, numPairs+2, 2, 6, 6, 6, 6);

        frame = new JFrame("Delete Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setOpaque(true);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
//        int numPairs = labels.length;
//
//        JPanel p = new JPanel(new SpringLayout());
//        for (int i = 0; i < numPairs; i++) {
//            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
//            p.add(l);
//            JTextField textField = new JTextField(10);
//            l.setLabelFor(textField);
//            p.add(textField);
//        }
//        JButton button = new JButton("Delete Farm");
//        p.add(button);
//        p.add(new Label(""));
//
//        makeCompactGrid(p, numPairs+2, 2, 6, 6, 6, 6);
//
//        frame = new JFrame("SpringForm");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        p.setOpaque(true);
//        frame.setContentPane(p);
//        frame.pack();
//        frame.setVisible(true);
    }
    private void onButtonDelete(){
        String[] fields = new String[numPairs];

        for(int i =0;i < numPairs; i++){
            fields[i]= textFields[i].getText();
            textFields[i].setText(""); //Clear after read
        }

//        dbHandler = Farm.getHandler();
//        dbHandler.deleteFarm(Integer.parseInt(fields[0]));

    }
    private void onButtonBack(){
        frame.dispose();
        DatabaseGui databaseWindow = new DatabaseGui();
        databaseWindow.showFrame();

    }
}
