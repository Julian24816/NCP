package w04;

import javax.swing.*;
import java.awt.*;

class ButtonDemoPanel extends JPanel {
    private JButton start, stop;

    /* encapsulate GUI field update in task
       posted to the a special thread:
       Event Dispatch Thread
     */
    private void setStopButtonLabel(String label){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                stop.setText(label);
            }
        });
    }

    ButtonDemoPanel() {
        start = new JButton("Start long task");
        stop = new JButton("Test if still responsive ");
        add(start);
        add(stop);
        stop.addActionListener(e -> { System.out.println("CLICK"); });
        start.addActionListener(e -> {
            Thread longTask = new Thread(() -> {
                try {
                    System.out.println("Start long task");
                    setStopButtonLabel("Press Me Now!");
                    Thread.sleep(5000);
                    System.out.println("End long task");
                    setStopButtonLabel("Voila!");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });
            longTask.start();
        });

    }
}
