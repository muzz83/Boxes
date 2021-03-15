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

// The TryAgainPanel displays at the end of the sequence
// and contains a Spinner object and button required to recommence
// the sequence.

public class TryAgainPanel extends JPanel {
    
    private JLabel tryAgain = new JLabel("Try Again?");
    
    public TryAgainPanel() {
        
        setSize(980,650);
        setBackground(Color.lightGray);
        setLayout(null);
        
        tryAgain.setSize(400,65);
        tryAgain.setFont(tryAgain.getFont().deriveFont(50.0f));
        tryAgain.setForeground(Color.YELLOW);        
        tryAgain.setLocation(370,235);
        add(tryAgain);
        Spinner spinner = new Spinner();
        
        spinner.setLocation(400,320);
        add(spinner);
    }    
}
