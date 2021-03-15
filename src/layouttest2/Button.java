/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package layouttest2;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author muss0013
 */

// This is the class that is used as buttons on the screen.
// This class also contains variables that are used to capture data
// and various counters required to keep track of where the program is
// in its sequence.

public class Button extends JButton {
    
        public static int permutationsCount = 1;
        public static int numberOfPermutations;
        public static int minimumWidth;
        public static int maximumWidth;
        public Color buttonColourOn = null;
        private Color buttonColourOff = null;
        public StopWatch clock = new StopWatch();        
        public static double[][] captureData;
        
        public Button() {
            buttonColourOn = Color.green;
            buttonColourOff = Color.GRAY;
            setBackground(buttonColourOn);
        }
        
        // Swaps the colour of the button.
        
        public void swapColour() {
            Color temp = buttonColourOn;
            buttonColourOn = buttonColourOff;
            buttonColourOff = temp;
            setBackground(buttonColourOn);
        }
}
