/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package layouttest2;

import java.awt.Color;
import static layouttest2.Button.captureData;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.util.*;

/**
 *
 * @author Michael
 */

// The Plot below is used to display the captured data.

public class Plot extends JPanel {
    
    private List points = new ArrayList();    
    
    private JLabel title = new JLabel("Movement Time vs Index of Difficulty");
    private JLabel yAxisLabel = new JLabel("Movement   Time   In   Milliseconds");
    private JLabel xAxisLabel = new JLabel("Index Of Difficulty (ID)");
    private JLabel cont = new JLabel("(Click to continue.)");    
    
    // The below list of points is useful for calibrating the graph if need be.
    
    private double[][] calibrationData = {{0,0},{0,100},{0,200},{0,300},{0,400},{0,500},
        {0,600},{0,700},{0,800},{0,900},{0,1000},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0}};        
    
    public Plot() {
        
        setSize(1200,865);
        setLayout(null);
        
        // Add title.
        
        title.setSize(500,80);
        title.setLocation(370,0);
        title.setFont(title.getFont().deriveFont(25.0f));
        add(title);
        
        // Add y axis label - this is the label that requires both the
        // TextIcon and RotateIcon classes.

        TextIcon ti = new TextIcon(yAxisLabel, yAxisLabel.getText());
        ti.setFont(yAxisLabel.getFont().deriveFont(18.0f));
        RotatedIcon ri = new RotatedIcon(ti,RotatedIcon.Rotate.UP);
        yAxisLabel.setIcon(ri);
        yAxisLabel.setSize(20,350);
        yAxisLabel.setLocation(10,230);
        add(yAxisLabel);
        
        // Add x axis label.
        
        xAxisLabel.setSize(350,20);
        xAxisLabel.setFont(xAxisLabel.getFont().deriveFont(18.0f));
        xAxisLabel.setLocation(490,825);
        add(xAxisLabel);
        
         // Add continue label.
        
        cont.setSize(150,15);
        cont.setLocation(525,65);
        add(cont);
    }
    
    public void paintComponent(Graphics g) {        
        
        // There's no need for this added variable (values), but it is useful
        // if the below graph needs calibration, so certain points can be tested.
        
        double[][] values = captureData;
        
        // The below method takes the recorded measurements as a double[]
        // and adds them to the list of points as 2D points.
        
        for (int i = 0; i < values.length; i++) {
            points.add(new Point2D.Double(values[i][0], values[i][1]));
        }
        
        // The below method takes the points and plots them on the
        // graph by filling an oval at the desired point.
        
        for(Iterator i = points.iterator(); i.hasNext();) {
            
            Point2D.Double pt = (Point2D.Double)i.next();
            g.setColor(Color.RED);
            g.fillOval((int)(140*(pt.x))+61,(int)((-pt.y+getHeight())*0.72) + 165, 16, 16);
        }
                
                int width = getWidth();
                int height = getHeight();
                setVisible(true);
                g.setColor(Color.BLACK);
                
                // Draw x-axis.
                
                g.drawLine(55, height - 70, width - 65, height - 70);
                
                // Draw y - axis.
                
                g.drawLine(70, 70, 70, height - 55);
                
                // Draw y-axis labels.
                
                for (int i = 1; i < 10; i++) {
                    String temp = 100*i + "--";
                    g.drawString(temp, 42, height - (66 + 72*(i)));
                }
                
                // Draw y-axis labels for y > 1000.
                
                for (int i = 10; i < 11; i++) {
                    String temp = 100*i + "--";
                    g.drawString(temp, 36, height - (66 + 72*(i)));
                }
                
                // Draw x-axis labels.
                
                for (int i = 1; i < 8; i++)
                {
                    g.drawString("|", 69 + 140*i, height - 60);
                    String temp = i + "";
                    g.drawString(temp, 66 + 140*i, height - 46);
                }
    }
}
