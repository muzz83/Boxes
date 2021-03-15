/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package layouttest2;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static layouttest2.Button.permutationsCount;
import static layouttest2.Button.numberOfPermutations;
import static layouttest2.Button.minimumWidth;
import static layouttest2.Button.maximumWidth;
import static layouttest2.Button.captureData;
import static layouttest2.Spinner.iterations;
import static layouttest2.Spinner.minWidth;
import static layouttest2.Spinner.maxWidth;
import static layouttest2.Spinner.begin;
/**
 *
 * @author Michael
 */
public class LayoutTest2 {

    /**
     * @param args the command line arguments
     */
    
    // Main method below, sets up the frame and adds the intro panel
    // which includes a Spinner object to determine variables.
    
    public static void main(String[] args) {
        
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(20, 20, 1200, 900);
        
        final IntroPanel intro = new IntroPanel();
        frame.add(intro);
        intro.setLocation(100,100);
        begin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                numberOfPermutations = Integer.parseInt(iterations.getValue().toString()) + 1;
                minimumWidth = Integer.parseInt(minWidth.getValue().toString());
                maximumWidth = Integer.parseInt(maxWidth.getValue().toString());   
                captureData = new double[numberOfPermutations-1][2];
                int[] widths = getWidths(numberOfPermutations,minimumWidth,maximumWidth);
                int[][] locations = getLocations(widths);
                double[] distances = getDistances(widths,locations);
                double[] difficultyIndices = calculateID(widths,distances);
                for (int i = 0; i < captureData.length; i++) {
                    captureData[i][0] = difficultyIndices[i];
                }
                begin.removeActionListener(this);
                frame.remove(intro);
                frame.repaint();
                paintFrame(frame,widths,locations,0);
            }
        });
        
        frame.setLayout(null);        
        frame.setVisible(true);
    }
    
    // The paintFrame method is the method that is called to add buttons
    // in their various positions on the screen. The buttons have action listeners
    // added to them which are set up to respond appropriately as the program
    // runs.
    
    public static void paintFrame(final JFrame frame, final int[] widths, final int[][] locations, int permutations) {
        
        final Button button1 = new Button();
        final Button button2 = new Button();
        button1.setSize(widths[permutations],widths[permutations]);
        button2.setSize(widths[permutations+1],widths[permutations+1]);
        button1.setLocation(locations[permutations][0],locations[permutations][1]);
        button2.setLocation(locations[permutations + 1][0],locations[permutations + 1][1]);
        button2.swapColour();
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (button1.buttonColourOn.equals(Color.green)) {
                    if (permutationsCount < numberOfPermutations-1) {
                        button1.clock.start();
                        button2.clock.stop();
                        if (permutationsCount > 1) {
                            captureData[permutationsCount-2][1] = button2.clock.getElapsedTime();
                        }                        
                        permutationsCount++;
                        frame.remove(button1);
                        button2.swapColour();
                        button1.setSize(widths[permutationsCount],widths[permutationsCount]);
                        button1.setLocation(locations[permutationsCount][0],locations[permutationsCount][1]);
                        button1.swapColour();
                        frame.add(button1);
                        frame.repaint();                
                    }
                    else {                        
                        button2.clock.stop();
                        button1.clock.start();
                        captureData[permutationsCount-2][1] = button2.clock.getElapsedTime();
                        permutationsCount++;
                        frame.remove(button1);
                        button2.swapColour();
                        frame.repaint();
                        if (numberOfPermutations%2 == 1) {
                            final FinishedPanel finishedPanel = new FinishedPanel();
                            finishedPanel.setLocation(100,100);
                            frame.add(finishedPanel);
                            frame.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    final Plot plot = new Plot();
                                    frame.remove(finishedPanel);
                                    frame.removeMouseListener(this);
                                    frame.add(plot);
                                    frame.repaint();
                                    frame.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            permutationsCount = 1;
                                            final TryAgainPanel tryAgain = new TryAgainPanel();
                                            frame.remove(plot);
                                            frame.removeMouseListener(this);
                                            tryAgain.setLocation(100,100);
                                            frame.add(tryAgain);
                                            frame.repaint();
                                            begin.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent event) {
                                                    numberOfPermutations = Integer.parseInt(iterations.getValue().toString()) + 1;
                                                    minimumWidth = Integer.parseInt(minWidth.getValue().toString());
                                                    maximumWidth = Integer.parseInt(maxWidth.getValue().toString());   
                                                    captureData = new double[numberOfPermutations-1][2];
                                                    int[] widths = getWidths(numberOfPermutations,minimumWidth,maximumWidth);
                                                    int[][] locations = getLocations(widths);
                                                    double[] distances = getDistances(widths,locations);
                                                    double[] difficultyIndices = calculateID(widths,distances);
                                                    for (int i = 0; i < captureData.length; i++) {
                                                        captureData[i][0] = difficultyIndices[i];
                                                    }
                                                    begin.removeActionListener(this);
                                                    frame.remove(tryAgain);
                                                    frame.repaint();
                                                    paintFrame(frame,widths,locations,0);
                                                }
                                            });                                            
                                        }                                        
                                    });
                                }
                            });
                        }
                    }
                }
            }
        });
        
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (button2.buttonColourOn.equals(Color.green)) {
                    if (permutationsCount < numberOfPermutations-1) {
                        button2.clock.start();                        
                        button1.clock.stop();
                        captureData[permutationsCount-2][1] = button1.clock.getElapsedTime();
                        permutationsCount++;
                        frame.remove(button2);
                        button2.swapColour();
                        button1.swapColour();
                        button2.setSize(widths[permutationsCount],widths[permutationsCount]);
                        button2.setLocation(locations[permutationsCount][0],locations[permutationsCount][1]);
                        frame.add(button2);
                        frame.repaint();                        
                    }
                    else {
                        button1.clock.stop();
                        button2.clock.start();
                        captureData[permutationsCount-2][1] = button1.clock.getElapsedTime();
                        permutationsCount++;
                        frame.remove(button2);
                        button1.swapColour();
                        frame.repaint();
                        if (numberOfPermutations%2 == 0) {
                            final FinishedPanel finishedPanel = new FinishedPanel();
                            finishedPanel.setLocation(100,100);
                            frame.add(finishedPanel);
                            frame.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    final Plot plot = new Plot();
                                    frame.remove(finishedPanel);
                                    frame.removeMouseListener(this);
                                    frame.add(plot);
                                    frame.repaint();
                                    frame.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            permutationsCount = 1;
                                            final TryAgainPanel tryAgain = new TryAgainPanel();
                                            frame.remove(plot);
                                            frame.removeMouseListener(this);
                                            tryAgain.setLocation(100,100);
                                            frame.add(tryAgain);
                                            frame.repaint();
                                            begin.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent event) {
                                                    numberOfPermutations = Integer.parseInt(iterations.getValue().toString()) + 1;
                                                    minimumWidth = Integer.parseInt(minWidth.getValue().toString());
                                                    maximumWidth = Integer.parseInt(maxWidth.getValue().toString());   
                                                    captureData = new double[numberOfPermutations-1][2];
                                                    int[] widths = getWidths(numberOfPermutations,minimumWidth,maximumWidth);
                                                    int[][] locations = getLocations(widths);
                                                    double[] distances = getDistances(widths,locations);
                                                    double[] difficultyIndices = calculateID(widths,distances);
                                                    for (int i = 0; i < captureData.length; i++) {
                                                        captureData[i][0] = difficultyIndices[i];
                                                    }
                                                    begin.removeActionListener(this);
                                                    frame.remove(tryAgain);
                                                    frame.repaint();
                                                    paintFrame(frame,widths,locations,0);
                                                }
                                            });
                                        }                                        
                                    });
                                }
                            });
                        }
                    }
                }
            }            
        });
        frame.add(button1);
        frame.add(button2);        
    }
    
    // The getWidths method takes the number of iterations, the minimum and
    // maximum widths and returns an int[] of random widths for the buttons.
    // This is the first method called in determining permutations.
    
    public static int[] getWidths(int noOfIterations, int minWidth, int MaxWidth) {
        
        Random randomGenerator = new Random();
        int[] widths = new int[noOfIterations];
        for (int i = 0; i < widths.length; i++) {
            int nextWidth = minWidth + randomGenerator.nextInt(MaxWidth - minWidth + 1);
            widths[i] = nextWidth;
        }
        return widths;        
    }
    
    // The getLocations method takes the widths of the buttons as a parameter
    // and determines locations for all the buttons at random. The method
    // ensures there is no overlap of buttons by using a while loop until
    // a non-overlapping position has been found.
    
    public static int[][] getLocations(int[] widths) {
        
        int[][] locations = new int[widths.length][2];
        Random randomGenerator = new Random();
        int width = 1184;
        int height = 861;
        
        int firstX = randomGenerator.nextInt(width - widths[0]);
        int firstY = randomGenerator.nextInt(height - widths[0]);
        locations[0][0] = firstX;
        locations[0][1] = firstY;        
        
        for (int i = 1; i < widths.length; i++) {
            
            boolean foundLocation = false;

            while (foundLocation == false) {
                
                int tryXHere = randomGenerator.nextInt(width - widths[i]);
                int tryYHere = randomGenerator.nextInt(height - widths[i]);
                int[] nextLocation = {tryXHere,tryYHere};
                if ((tryXHere + widths[i]) < locations[i-1][0]) {
                    locations[i] = nextLocation;
                    foundLocation = true;
                }                
                else if (tryXHere > (locations[i-1][0] + widths[i-1])) {
                    locations[i] = nextLocation;
                    foundLocation = true;
                }                
                else if ((tryYHere + widths[i]) < locations[i-1][1]) {
                    locations[i] = nextLocation;
                    foundLocation = true;
                }                
                else if (tryYHere > (locations[i-1][1] + widths[i-1])) {
                    locations[i] = nextLocation;
                    foundLocation = true;
                }                
                else {
                    foundLocation = false;
                }
            }
        }        
        return locations;
    }
    
    // Determines the distance between consecutive buttons.
    
    public static double[] getDistances(int[] widths, int[][] locations) {
        
        double[] distances = new double[widths.length - 1];
        for (int i = 0; i < distances.length; i++) {
            double xDistance = (locations[i][0] + (double)(widths[i])/2)
                    - (locations[i+1][0] + (double)(widths[i+1])/2);
            double yDistance = (locations[i][1] + (double)(widths[i])/2)
                    - (locations[i+1][1] + (double)(widths[i+1])/2);
            distances[i] = Math.sqrt((Math.pow(xDistance,2)) + (Math.pow(yDistance,2)));
        }
        return distances;
    }
    
    // Determines the index of difficulty for each permutation.
    
    public static double[] calculateID(int[] widths, double[] distances) {
        double[] indexOfDifficulty = new double[distances.length];
        for (int i = 0; i < distances.length; i++) {
            indexOfDifficulty[i] = (Math.log((2*distances[i])/(double)widths[i+1]))/Math.log(2);
        }
        return indexOfDifficulty;
    }
}
