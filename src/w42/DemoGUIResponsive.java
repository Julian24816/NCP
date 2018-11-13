package w42;

import javax.swing.*;
import java.awt.*;

public class DemoGUIResponsive extends JFrame {

    private static class ButtonDemoPanel extends JPanel {
        private JButton start, stop;

        private ButtonDemoPanel() {
            start = new JButton("Start long task");
            stop = new JButton("Test if still responsive ");
            add(start);
            add(stop);
            stop.addActionListener(e -> System.out.println("CLICK"));
            start.addActionListener(e -> new Thread(() -> {
                try {
                    System.out.println("Start long task");
                    Thread.sleep(5000);
                    System.out.println("End long task");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }).start());
        }
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(DemoGUIResponsive::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("(Un-)responsive GUI Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(400, 100));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        frame.getContentPane().add(new ButtonDemoPanel());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

