package ca.ubc.cs304.ui.OldGUI;

import java.awt.*;

import java.awt.event.ActionListener;

import javax.swing.*;

public class DatabaseGui extends JFrame{
    private  JFrame frame;

    public DatabaseGui() {
        super("Agriculture Database");
    }
    public void showFrame() {
        frame = new JFrame("Agriculture Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        addButton("1. Insert Farm and Supervisor",container, e -> openWindow(1));
        addButton("2. Insert Farming Equipments" ,container,e -> openWindow(2));
        addButton("3. Delete Tool",container, e -> openWindow(3));
        addButton("4. Delete Farm",container, e -> openWindow(4));
        addButton("5. Update Tool",container,e -> openWindow(5));
        addButton("6. Update Supervisor",container,e -> openWindow(6));
//        addButton("7. Show Farming Equipments",container);
//        addButton("8. Show Farm and Supervisor",container);
        addButton("9. Quit",container,e -> openWindow(9));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
    private void addButton(String text, Container container, ActionListener listener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(listener);
        container.add(button);
    }
    private void  openWindow( int win){
        frame.dispose();
        switch (win){
            case 1:
                InsertFarmWindow window = new InsertFarmWindow();
                window.showFrame();
                break;
            case 2:
                InsertEquipmentWindow window2 = new InsertEquipmentWindow();
                window2.showFrame();
                break;
            case 3:
                ToolDeleteWindow window3 = new ToolDeleteWindow();
                window3.showFrame();
                break;
            case 4:
                FarmDeleteWindow window4 = new FarmDeleteWindow();
                window4.showFrame();
                break;
            case 5:
                Tool_IDUpdateWindow window5 = new Tool_IDUpdateWindow();
                window5.showFrame();
                break;
            case 6:
                SupervisorUpdateWindow window6 = new SupervisorUpdateWindow();
                window6.showFrame();
                break;
            case 9:
                break;

        }
    }


}
