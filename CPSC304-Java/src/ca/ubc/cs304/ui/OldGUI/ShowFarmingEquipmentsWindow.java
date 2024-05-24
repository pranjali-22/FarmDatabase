package ca.ubc.cs304.ui.OldGUI;

import javax.swing.*;
import java.awt.*;

public class ShowFarmingEquipmentsWindow extends JFrame {
    // Utility method from SpringUtilities.java
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
        String[] labels = {"Tool Name" , "Tool Usage", "Manufacture", "Tool ID", "Tool Price", "Farm ID"};
        int numPairs = labels.length;

        JPanel p = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);

        }
        JButton button = new JButton("Back");
        p.add(button);
        for (int i = 0; i < numPairs-1; i++) {
            JLabel l = new JLabel(" ");
            p.add(l);

        }




//        System.out.printf("name : %-10.10s", model.getTool_name());
//        System.out.printf("usage : %-20.20s", model.getTool_usage());
//        System.out.printf("Manufacture : %-20.20s", model.getManufacturer());
//        System.out.printf("Tool_id : %-20.20s", model.getTool_id());
//        System.out.printf("Tool_price : %-20.20s", model.getPrice());
//        System.out.printf("Farm_id : %-20.20s", model.getFarm_id());

        makeCompactGrid(p, 2, 6, 6, 6, 6, 6);

        JFrame frame = new JFrame("SpringForm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setOpaque(true);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
    }
}
