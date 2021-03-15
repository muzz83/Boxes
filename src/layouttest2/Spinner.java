/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package layouttest2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Michael
 */

// The spinner clase below extends JPanel and contains three
// spinners required to set up the number of iterations, the minimum width
// and the maximum width.

public class Spinner extends JPanel {    
    
    private static JLabel iterationsLabel = new JLabel("No. Of Iterations   ");
    private static JLabel minWidthLabel = new JLabel("Minimum Width   ");
    private static JLabel maxWidthLabel = new JLabel("Maximum Width   ");
    private static SpinnerNumberModel iterationsModel = new SpinnerNumberModel(20, 6, 100, 1);
    private static SpinnerNumberModel minWidthModel = new SpinnerNumberModel(16, 8, 50, 1);
    private static SpinnerNumberModel maxWidthModel = new SpinnerNumberModel(80, 50, 300, 1);
    public static JSpinner iterations = new JSpinner(iterationsModel);
    public static JSpinner minWidth = new JSpinner(minWidthModel);
    public static JSpinner maxWidth = new JSpinner(maxWidthModel);
    public static JButton begin = new JButton("BEGIN");
    
    public Spinner() {
        
        // The Spinner class uses a GridBagLayout to layour the components.
        
        setSize(200,100);
        setLayout(new GridBagLayout());  
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(iterationsLabel, c);
        
        c.gridx = 1;
        c.gridy = 0;
        add(iterations, c);        

        c.gridx = 0;
        c.gridy = 1;
        add(minWidthLabel, c);        

        c.gridx = 1;
        c.gridy = 1;
        add(minWidth, c);
        
        c.gridx = 0;
        c.gridy = 2;
        add(maxWidthLabel, c);
        
        c.gridx = 1;
        c.gridy = 2;
        add(maxWidth, c);
        
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 0;
        c.gridy = 3;
        add(begin, c);  
    }
}
