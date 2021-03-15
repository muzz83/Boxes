/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package layouttest2;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Michael
 */

// The IntroPanel for the program.

public class IntroPanel extends JPanel {
    
    private JLabel title = new JLabel("Fitts's Law Experiment"); 
    private String text = "<html>This is an interactive GUI based "
            + "on Fitts's Law and the original experiment performed by Fitts. "
            + "A random sequence of different sized and located squares will "
            + "appear on the screen and you will be timed while you click from "
            + "from one square to the next. Begin by clicking the green square, "
            + "then select the next green square until the number of iterations "
            + "has been completed. Focus on speed and accuracy. Your times "
            + "will be displayed at the end of the sequence. Choose the "
            + "number of iterations below, as well as the minimum and "
            + "maximum width of the squares, then click the BEGIN button to "
            + "get started.";
    private JLabel intro = new JLabel("<html><div style=\"text-align: center;\">" + text + "</html>");
    private Spinner spinner = new Spinner();
    
    public IntroPanel() {
        
        // Set up the panel, components, and add them to the panel.
        
        setSize(980,650);
        setLayout(null);
        setBackground(Color.lightGray);
        
        spinner.setLocation(400,470);
        title.setFont(title.getFont().deriveFont(35.0f));
        title.setForeground(Color.YELLOW);
        title.setSize(400, 100);
        title.setLocation(300,0);
        intro.setFont(intro.getFont().deriveFont(15.0f));
        intro.setForeground(Color.BLACK);
        intro.setSize(350, 300);
        intro.setLocation(325,135);
        
        add(title);
        add(intro);
        add(spinner);
    }
}
