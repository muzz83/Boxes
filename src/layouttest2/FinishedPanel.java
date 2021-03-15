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

// The FinishedPanel for the program.

public class FinishedPanel extends JPanel {
    
    private JLabel finished = new JLabel("Finished!");
    private JLabel cont= new JLabel("Click to continue...."); 
    
    public FinishedPanel() {
        
        // Set up the panel and add components.
        
        setSize(980,650);
        setBackground(Color.lightGray);
        
        finished.setSize(400,50);
        finished.setFont(finished.getFont().deriveFont(50.0f));
        finished.setForeground(Color.YELLOW);        
        finished.setLocation(370,270);
        add(finished);
        
        cont.setForeground(Color.YELLOW);
        cont.setFont(cont.getFont().deriveFont(16.0f));
        cont.setLocation(415,325);
        cont.setSize(200,20);
        add(cont);        
    }    
}
