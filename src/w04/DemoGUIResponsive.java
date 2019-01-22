package w04;

import javax.swing.*;
import java.awt.*;

public class DemoGUIResponsive extends JFrame {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Responsive GUI Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(175, 100));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.getContentPane().add(new ButtonDemoPanel());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
